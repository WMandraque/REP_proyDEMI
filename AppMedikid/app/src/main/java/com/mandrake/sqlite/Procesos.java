package com.mandrake.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mandrake.model.ApoderadoModel;
import com.mandrake.utils.DBConstantes;

/**
 * Created by EDSON on 28/06/2015.
 */
public class Procesos {

    private DbHelper dbConnection;

    public Procesos(Context context) {
        dbConnection = new DbHelper(context);
    }

    public void InsertarApoderado(ApoderadoModel entidadApoderado)
    {
        String values = String.format("%s, '%s', '%s', '%s', '%s'", String.valueOf(entidadApoderado.getIdApoderado()),
                                                            entidadApoderado.getDNI(),
                                                            entidadApoderado.getNombreCompleto(),
                                                            entidadApoderado.getEmail(),
                                                            entidadApoderado.getContrasena());
        SQLiteDatabase db = dbConnection.getWritableDatabase();
        db.execSQL(String.format("INSERT INTO %s (%s) VALUES (%s)", DBConstantes.TB_APODERADO, DBConstantes.APODERADO_COLS, values));
    }

    public boolean ApoderadoLogueado()
    {
        boolean rpta = false;

        SQLiteDatabase db = dbConnection.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT COUNT(*) FROM %s", DBConstantes.TB_APODERADO), null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            int value =cursor.getInt(0);
            if (value > 0)
                rpta = true;
        }

        return rpta;
    }

}
