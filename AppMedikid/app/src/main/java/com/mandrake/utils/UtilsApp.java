package com.mandrake.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by EDSON on 28/06/2015.
 */
public class UtilsApp {

    public static void Mensaje(Context context, String msg)
    {
        Toast.makeText(context,
                msg,
                Toast.LENGTH_LONG).show();
    }

}
