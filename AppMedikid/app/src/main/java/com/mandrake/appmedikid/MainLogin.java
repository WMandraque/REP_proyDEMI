package com.mandrake.appmedikid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mandrake.application.Configuration;
import com.mandrake.model.ApoderadoModel;
import com.mandrake.sqlite.Procesos;
import com.mandrake.utils.UtilsApp;

import org.json.JSONException;
import org.json.JSONObject;


public class MainLogin extends Activity
{
    private EditText txtCorreo, txtClave;
    private Button btnEntrar;//, btnRegistrar;

    private ProgressDialog pDialog;

    final String TAG = MainLogin.class.getSimpleName();

    Procesos serv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        serv = new Procesos(MainLogin.this);
        /*
        ApoderadoModel entidad = new ApoderadoModel();
        entidad.setIdApoderado(1);
        entidad.setNombreCompleto("Edson Flores");
        entidad.setDNI("12345678");
        entidad.setContrasena("123");
        entidad.setEmail("efp@gmail.com");

        serv.InsertarApoderado(entidad);
        */
        if (serv.ApoderadoLogueado())
        {
            startActivity(new Intent(MainLogin.this, MainPrincipal.class));
            finish();
        }

        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        //btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        txtCorreo = (EditText)findViewById(R.id.txtCorreo);
        txtClave = (EditText)findViewById(R.id.txtClave);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
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

                //Login(email, clave);

                startActivity(new Intent(MainLogin.this, MainPrincipal.class));
                finish();
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

        //Service Login

        //String url = "http://localhost:24364/Host/ApoderadoService.svc/LoginApoderado/jcarlos20@gmail.com/123";
        String url = String.format("http://localhost:24364/Host/ApoderadoService.svc/LoginApoderado/%s/%s", email, pass);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d(TAG, jsonObject.toString());

                        try {
                            ApoderadoModel entidad = new ApoderadoModel();

                            entidad.setIdApoderado(jsonObject.getInt("IdApoderado"));
                            entidad.setDNI(jsonObject.getString("Dni"));
                            entidad.setNombreCompleto(jsonObject.getString("NombreCompleto"));
                            entidad.setEmail(jsonObject.getString("Email"));
                            entidad.setContrasena(jsonObject.getString("Contrasena"));

                            Log.d("Result", entidad.getNombreCompleto());
                            serv.InsertarApoderado(entidad);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            UtilsApp.Mensaje(getApplicationContext(),
                                        "Error: " + e.getMessage());
                        }

                        hideDialog();
                    }
                }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Log.d(TAG, "Error: " + error.getMessage());
                            UtilsApp.Mensaje(getApplicationContext(),
                                    "Error: " + error.getMessage());

                            hideDialog();
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
