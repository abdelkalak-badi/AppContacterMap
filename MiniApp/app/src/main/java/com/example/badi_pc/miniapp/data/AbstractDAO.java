package com.example.badi_pc.miniapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AbstractDAO {
    protected SQLiteDatabase db=null;
    protected  MyBDHelper dbHelper=null;

    public AbstractDAO(Context context) {
        this.dbHelper = new MyBDHelper(context);
    }

    public SQLiteDatabase openWrite() {
        db = dbHelper.getWritableDatabase();
        return db;
    }

    public SQLiteDatabase openRead() {
        db = dbHelper.getReadableDatabase();
        return db;
    }
    public void close() {
        db.close();
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
