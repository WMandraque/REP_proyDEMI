package com.mandrake.appmedikid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mandrake.model.PacienteModel;
import com.mandrake.utils.Constantes;


public class MainReserva extends Activity
{
    Spinner spPaciente;
    EditText txtFecha, txtMotivo;
    Button btnReservar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reserva);

        spPaciente = (Spinner)findViewById(R.id.spPaciente);
        txtFecha = (EditText)findViewById(R.id.txtFecha);
        txtMotivo = (EditText)findViewById(R.id.txtMotivo);
        btnReservar = (Button)findViewById(R.id.btnReservarCita);

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

                PacienteModel paciente = (PacienteModel)spPaciente.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_reserva, menu);
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

}
