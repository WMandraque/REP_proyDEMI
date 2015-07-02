package com.mandrake.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by EDSON on 28/06/2015.
 */
public class UtilsApp {

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

}
