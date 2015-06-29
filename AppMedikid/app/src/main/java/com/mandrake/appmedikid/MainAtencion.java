package com.mandrake.appmedikid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mandrake.application.Configuration;
import com.mandrake.model.PacienteModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainAtencion extends Activity
{
    Spinner spPaciente;

    private ArrayList<PacienteModel> pacientes;
    private PacienteModel pacienteSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_atencion);

        spPaciente = (Spinner)findViewById(R.id.spPaciente);

        //CargarPacientes();
        CargarSpinnerPaciente();
    }


    @Override
    protected void onResume() {
        super.onResume();

        spPaciente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String idSel = String.valueOf(parent.getItemIdAtPosition(position));
                String item = parent.getItemAtPosition(position).toString();

                pacienteSeleccionado = null;
                for(int i=0; i<pacientes.size(); i++)
                {
                    pacienteSeleccionado = pacientes.get(i);
                    if (pacienteSeleccionado.getNombreCompleto().equals(item))
                        break;
                }

                Log.d("PACIENTE", String.format("Id: %s - IdPaciente: %s - Nombre: %s", pacienteSeleccionado.getId(),
                        pacienteSeleccionado.getIdPaciente(),
                        pacienteSeleccionado.getNombreCompleto()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_atencion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    public void CargarSpinnerPaciente()
    {
        pacientes = new ArrayList<PacienteModel>();

        PacienteModel entidad = new PacienteModel();
        entidad.setIdPaciente(0);
        entidad.setNombreCompleto("-- Seleccione --");
        pacientes.add(entidad);

        entidad = new PacienteModel();
        entidad.setIdPaciente(1);
        entidad.setNombreCompleto("Edson Flores");
        pacientes.add(entidad);

        entidad = new PacienteModel();
        entidad.setIdPaciente(2);
        entidad.setNombreCompleto("Benito Perez");
        pacientes.add(entidad);

        ArrayAdapter<PacienteModel> adapter = new ArrayAdapter<PacienteModel>(this, android.R.layout.simple_spinner_item, pacientes);
        spPaciente.setAdapter(adapter);
    }

    private void CargarPacientes()
    {
        pacientes = new ArrayList<PacienteModel>();

        String url = "http://localhost:24364/Host/ApoderadoService.svc/GetPacientesByApoderado/1";
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

                                    paciente.setIdPaciente(json.getInt("IdPaciente"));
                                    paciente.setNombreCompleto(json.getString("NombreCompleto"));

                                    pacientes.add(paciente);
                                }
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }

                            ArrayAdapter<PacienteModel> adapter = new ArrayAdapter<PacienteModel>(MainAtencion.this, android.R.layout.simple_spinner_item, pacientes);
                            spPaciente.setAdapter(adapter);
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

}
