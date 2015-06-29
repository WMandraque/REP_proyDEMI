package com.mandrake.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mandrake.utils.DBConstantes;

/**
 * Created by EDSON on 28/06/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, DBConstantes.DB_NAME, null, DBConstantes.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConstantes.CREATE_TBAPODERADO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBConstantes.DROP_TBAPODERADO);
        onCreate(db);
    }

}
