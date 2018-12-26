package com.example.badi_pc.miniapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContacterDAO extends AbstractDAO {
    public static final String TABLE_NAME="Contacter";//Nom de la table STOCK
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


    public ContacterDAO(Context context) {
        super(context);
    }



    public boolean add(Contacter prod) {
        boolean ok=true;
        try {
            SQLiteDatabase db = openWrite();
            ContentValues values = new ContentValues();
            values.put(ContacterDAO.KEY_ID, prod.getID());
            values.put(ContacterDAO.KEY_Nom, prod.getNom());
            values.put(ContacterDAO.KEY_Prenom, prod.getPrenom());
            values.put(ContacterDAO.KEY_Img, prod.getImg());
            values.put(ContacterDAO.KEY_Email, prod.getEmail());
            values.put(ContacterDAO.KEY_Phone, prod.getPhone());
            values.put(ContacterDAO.KEY_SiteWeb, prod.getSiteWeb());
            values.put(ContacterDAO.KEY_MapLongitude, prod.getMapLongitude());
            values.put(ContacterDAO.KEY_MapLatitude, prod.getMapLatitude());

            db.insert(ContacterDAO.TABLE_NAME, null, values);
            close();
            // fermer la connection BD
        }catch (Exception e){
            ok=false;
        }
        return ok;
        }

    public boolean delete(Contacter prod) {
        boolean ok=true;
        try {
        SQLiteDatabase db = openWrite();
        db.delete(TABLE_NAME, ContacterDAO.KEY_ID + " = ?", new String[] { String.valueOf(prod.getID()) });
        db.close();
    }catch (Exception e){
        ok=false;
    }
        return ok;
    }
   public Contacter findByID(int id) {
       Contacter P= new Contacter();
        try {
            SQLiteDatabase db = openRead();
            Cursor cursor = db.query(TABLE_NAME,new String[] {
                            ContacterDAO.KEY_ID,
                            ContacterDAO.KEY_Nom,
                            ContacterDAO.KEY_Prenom,
                            ContacterDAO.KEY_Img,
                            ContacterDAO.KEY_Email,
                            ContacterDAO.KEY_SiteWeb,
                            ContacterDAO.KEY_MapLatitude,
                            ContacterDAO.KEY_MapLongitude,
                            ContacterDAO.KEY_Phone},ContacterDAO.KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null,
                    null, null);
            if (cursor != null)
                cursor.moveToFirst();
            if (cursor != null)
                 P = new Contacter(
                         cursor.getString(0),
                         cursor.getString(1),
                         cursor.getString(2),
                         cursor.getString(3),
                         cursor.getString(4),
                         cursor.getString(5),
                         cursor.getString(6),
                         cursor.getString(7),
                         cursor.getString(8));
        }catch (Exception e){
            return P= new Contacter();
        }

        return P;     }
    public List<Contacter> findAll() {

    List<Contacter> list =new ArrayList<Contacter>();
    Contacter p;
        try {
            SQLiteDatabase db = openRead();
            Cursor cursor = db.rawQuery("Select * from "+TABLE_NAME,null);
            if (cursor != null)
                cursor.moveToFirst();
            do {
                p = new Contacter(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8));
                list.add(p);
            }while (cursor.moveToNext());
        }catch (Exception e){
            return null;
        }

        return list;
    }


    }
