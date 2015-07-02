package com.mandrake.appmedikid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mandrake.application.Configuration;
import com.mandrake.model.PacienteModel;
import com.mandrake.sqlite.Procesos;
import com.mandrake.utils.Constantes;
import com.mandrake.utils.UtilsApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainLogin extends Activity
{
    private EditText txtCorreo, txtClave;
    private Button btnEntrar;//, btnRegistrar;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        if (Procesos.Instance(MainLogin.this).ApoderadoLogueado())
        {
            CargarPacientes();
            startActivity(new Intent(MainLogin.this, MainPrincipal.class));
            finish();
        }

        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        txtCorreo = (EditText)findViewById(R.id.txtCorreo);
        txtClave = (EditText)findViewById(R.id.txtClave);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Espere por favor...");
        pDialog.setCancelable(false);

    }

    @Override
    protected void onResume()
    {
        super.onResume();

        btnEntrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email = txtCorreo.getText().toString();
                String clave = txtClave.getText().toString();

                if (email.length() < 1 || clave.length() < 1)
                {
                    UtilsApp.Mensaje(getApplicationContext(), "Ingrese sus Credenciales");
                    return;
                }
                Login(email, clave);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void Login(String email, String pass)
    {
        showDialog();

        //String url = "http://192.168.1.40:50/Host/ApoderadoService.svc/LoginApoderado/jcarlos20@gmail.com/123";
        String url = String.format(Constantes.URL_MEDIKID_LOGIN, email, pass);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        try {
                            if (jsonObject != null)
                            {
                                Constantes.apoderado.setIdApoderado(jsonObject.getInt("IdApoderado"));
                                Constantes.apoderado.setDNI(jsonObject.getString("Dni"));
                                Constantes.apoderado.setNombreCompleto(jsonObject.getString("NombreCompleto"));
                                Constantes.apoderado.setEmail(jsonObject.getString("Email"));
                                Constantes.apoderado.setContrasena(jsonObject.getString("Contrasena"));

                                CargarPacientes();

                                Procesos.Instance(MainLogin.this).InsertarApoderado(Constantes.apoderado);

                                startActivity(new Intent(MainLogin.this, MainPrincipal.class));
                                finish();
                            }

                        } catch (JSONException je) {
                            UtilsApp.InformarError(MainLogin.this, "Json Error", je.getMessage());
                            je.printStackTrace();
                        } catch (Exception e){
                            UtilsApp.InformarError(MainLogin.this, "Ha ocurrido un error inesperado", e.getMessage());
                            e.printStackTrace();
                        } finally {
                            hideDialog();
                        }
                    }
                }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            UtilsApp.InformarError(MainLogin.this, "Credenciales Incorrectas", error.getMessage());
                            hideDialog();
                        }
                    }
            );

        Configuration.getInstance().addToRequestQueue(jsonRequest);
    }


    private void CargarPacientes()
    {
        Constantes.pacientes.add(new PacienteModel(0, 0, "-- Seleccione --"));

        String url = String.format(Constantes.URL_MEDIKID_GETPACIENTES, Constantes.apoderado.getIdApoderado());
        JsonArrayRequest jsonRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {

                        if (jsonArray.length() > 0)
                        {
                            try
                            {
                                JSONObject json;
                                PacienteModel paciente;
                                for (int i=0; i<jsonArray.length();i++)
                                {
                                    json = jsonArray.getJSONObject(i);
                                    paciente = new PacienteModel();

                                    paciente.setId(i);
                                    paciente.setIdPaciente(json.getInt("IdPaciente"));
                                    paciente.setNombreCompleto(json.getString("NombreCompleto"));

                                    Constantes.pacientes.add(paciente);
                                }
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        );

        Configuration.getInstance().addToRequestQueue(jsonRequest);
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
