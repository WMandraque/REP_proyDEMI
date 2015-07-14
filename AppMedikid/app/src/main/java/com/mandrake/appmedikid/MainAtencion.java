package com.mandrake.appmedikid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mandrake.adapters.ListadoAdapter;
import com.mandrake.application.Configuration;
import com.mandrake.model.AtencionModel;
import com.mandrake.model.PacienteModel;
import com.mandrake.utils.Constantes;
import com.mandrake.utils.UtilsApp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainAtencion extends Activity
{
    Spinner spPaciente;
    ListView lvLista;
    ListadoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_atencion);

        spPaciente = (Spinner)findViewById(R.id.spPaciente);
        lvLista = (ListView)findViewById(R.id.lvLista);

        UtilsApp.CrearDialog(this, "Cargando..");
        while(spPaciente.getCount() < 1)
        {
            CargarSpinnerPacientes();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        spPaciente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (spPaciente.getSelectedItemPosition() > 0)
                {
                    PacienteModel paciente = (PacienteModel)spPaciente.getSelectedItem();
                    CargarAtencionesByPaciente(paciente.getIdPaciente());
                }
                else{
                    adapter = new ListadoAdapter(getApplicationContext(), new ArrayList<AtencionModel>());
                    lvLista.setAdapter(adapter);
                }
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


    private void CargarSpinnerPacientes()
    {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                                                Constantes.pacientes.toArray(new PacienteModel[Constantes.pacientes.size()]));
        spPaciente.setAdapter(adapter);
    }

    private void CargarAtencionesByPaciente(int idPaciente)
    {
        UtilsApp.showDialog();
        final ArrayList<AtencionModel> atenciones = new ArrayList<AtencionModel>();

        String url = String.format(Constantes.URL_MEDIKID_GETATENCIONES, idPaciente);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {

                        if (jsonArray.length() > 0)
                        {
                            try
                            {
                                JSONObject json;
                                AtencionModel atencion;
                                for (int i=0; i<jsonArray.length();i++)
                                {
                                    json = jsonArray.getJSONObject(i);
                                    atencion = new AtencionModel();

                                    atencion.setId(json.getInt("IdAtencion"));

                                    atenciones.add(atencion);

                                    adapter = new ListadoAdapter(getApplicationContext(), atenciones);
                                    lvLista.setAdapter(adapter);

                                    UtilsApp.hideDialog();
                                }
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                                UtilsApp.hideDialog();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        UtilsApp.hideDialog();
                    }
                }
        );

        try
        {
            Configuration.getInstance().addToRequestQueue(jsonRequest);
        } finally {
            UtilsApp.hideDialog();
        }
    }

}
