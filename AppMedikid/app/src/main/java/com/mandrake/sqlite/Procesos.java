package com.mandrake.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mandrake.model.ApoderadoModel;
import com.mandrake.utils.Constantes;
import com.mandrake.utils.DBConstantes;

/**
 * Created by EDSON on 28/06/2015.
 */
public class Procesos {

    private static Procesos _instance = null;
    private DbHelper dbConnection;

    public static Procesos Instance(Context context)
    {
        if (_instance == null)
            _instance = new Procesos(context);

        return _instance;
    }

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
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s", DBConstantes.TB_APODERADO), null);

        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                ApoderadoModel entidadApoderado = new ApoderadoModel(
                                                                cursor.getInt(0),
                                                                cursor.getInt(1),
                                                                cursor.getString(2),
                                                                cursor.getString(3),
                                                                cursor.getString(4),
                                                                cursor.getString(5)
                                                             );
                Constantes.apoderado = entidadApoderado;
                rpta = true;
            }
        }

        return rpta;
    }

}
