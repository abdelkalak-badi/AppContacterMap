package com.example.badi_pc.miniapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.badi_pc.miniapp.data.Contacter;

import java.util.ArrayList;


public class MyAdapter extends ArrayAdapter<Contacter> {


    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Contacter> objects) {
        super(context, resource, objects);
    }




    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=layoutInflater.inflate(R.layout.cellule,null);
        Contacter p=getItem(position);
        TextView nom=(TextView)v.findViewById(R.id.textNom);
        TextView prenom=(TextView)v.findViewById(R.id.textPrenom);
        TextView num=(TextView)v.findViewById(R.id.textPhone);
        TextView email=(TextView)v.findViewById(R.id.textEmail);
        TextView siteweb=(TextView)v.findViewById(R.id.textSite);
        ImageView imageView=(ImageView)v.findViewById(R.id.textImage);
        nom.setText(p.getNom()+" ");
        prenom.setText(p.getPrenom()+"");
        num.setText(p.getPhone()+"");
        siteweb.setText(p.getSiteWeb()+"");
        email.setText(p.getEmail()+"");
        try {
            imageView.setImageResource(Integer.parseInt(p.getImg()));
        }catch (Exception e){
            imageView.setImageResource(R.drawable.businessman);
        }




        return v;
    }

}
