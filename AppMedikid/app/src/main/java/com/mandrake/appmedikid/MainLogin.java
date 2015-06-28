package com.mandrake.appmedikid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mandrake.application.Configuration;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;


public class MainLogin extends Activity
{
    private EditText txtCorreo, txtClave;
    private Button btnEntrar, btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);


        btnEntrar=(Button)findViewById(R.id.btnEntrar);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);
        txtCorreo=(EditText)findViewById(R.id.txtCorreo);
        txtClave=(EditText)findViewById(R.id.txtClave);

    }

    @Override
    protected void onResume()
    {
        super.onResume();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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



}
