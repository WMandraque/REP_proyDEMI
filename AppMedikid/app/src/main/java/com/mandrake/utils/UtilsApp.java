package com.mandrake.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by EDSON on 28/06/2015.
 */
public class UtilsApp {

    private static ProgressDialog pDialog;

    public static void Mensaje(Context context, String msg)
    {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void Imprimir(String errorMsg)
    {
        Log.d("AppError", errorMsg);
    }

    public static void InformarError(Context context, String customText, String errorMsg)
    {
        Mensaje(context, customText);
        Imprimir(errorMsg);
    }

    public static void CrearDialog(Activity activity, String texto)
    {
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage(texto);
        pDialog.setCancelable(false);
    }

    public static void showDialog()
    {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public static void hideDialog()
    {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
