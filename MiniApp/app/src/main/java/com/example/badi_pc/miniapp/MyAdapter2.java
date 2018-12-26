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


public class MyAdapter2 extends ArrayAdapter<Contacter> {


    public MyAdapter2(@NonNull Context context, int resource, @NonNull ArrayList<Contacter> objects) {
        super(context, resource, objects);
    }




    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=layoutInflater.inflate(R.layout.testmenu,null);
        Contacter p=getItem(position);
        TextView nom=(TextView)v.findViewById(R.id.textnom);

        nom.setText(p.getNom()+" ");


        return v;
    }

}
