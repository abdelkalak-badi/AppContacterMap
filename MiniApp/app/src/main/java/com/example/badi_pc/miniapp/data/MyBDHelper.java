package com.example.badi_pc.miniapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MyBDHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    //Version DB
    public static final String DATABASE_NAME="demo_DB.db";//Nom BD
    public static final String TABLE_Contacter="Contacter";//Nom de la table STOCK
    // Nom de colonnes Table STOCK
    public static final String KEY_ID="ID";
    public static final String KEY_Nom="Nom";
    public static final String KEY_Prenom="Prenom";
    public static final String KEY_Img="Img";
    public static final String KEY_Email="Email";
    public static final String KEY_Phone="Phone";
    public static final String KEY_SiteWeb="SiteWeb";
    public static final String KEY_MapLongitude ="MapLongitude";
    public static final String KEY_MapLatitude ="MapLatitude";
    public MyBDHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STOCK_TABLE =
                "CREATE TABLE " + TABLE_Contacter + "("
                        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_Nom + " TEXT,"
                        + KEY_Prenom + " TEXT,"
                        + KEY_Img + " TEXT,"
                        + KEY_Email + " TEXT,"
                        + KEY_Phone + " TEXT,"
                        + KEY_SiteWeb + " TEXT,"
                        + KEY_MapLongitude + " TEXT,"
                        + KEY_MapLatitude + " TEXT" + ")";
        db.execSQL(CREATE_STOCK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Contacter);
        // Create tables une autre fois
        onCreate(db);
    }
}
