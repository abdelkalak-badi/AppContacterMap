package com.example.badi_pc.miniapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ListeImgActivity extends AppCompatActivity {
Intent i;
Bundle ii;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_img);
        final SharedPreferences sharedPreferences=getSharedPreferences("pass",MODE_PRIVATE);

        i=getIntent();
        getSupportActionBar().hide();
        final ImageView img1=(ImageView)findViewById(R.id.image1);
        ImageView img2=(ImageView)findViewById(R.id.image2);
        ImageView img3=(ImageView)findViewById(R.id.image3);
        ImageView img4=(ImageView)findViewById(R.id.image4);
        ImageView img5=(ImageView)findViewById(R.id.image5);
        ImageView img6=(ImageView)findViewById(R.id.image6);
        ImageView img7=(ImageView)findViewById(R.id.image7);
        ImageView img8=(ImageView)findViewById(R.id.image8);
        ImageView img9=(ImageView)findViewById(R.id.image9);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("img",R.drawable.a);
                sharedPreferences.edit();
                editor.commit();
                ii.putInt("img",R.drawable.a);
                i.putExtras(ii);
                setResult(44,i);
                finish();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("img",R.drawable.b);
                sharedPreferences.edit();
                editor.commit();
                ii.putInt("img",R.drawable.b);
                i.putExtras(ii);
                setResult(44,i);
                finish();
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("img",R.drawable.c);
                sharedPreferences.edit();
                editor.commit();
                ii.putInt("img",R.drawable.c);
                i.putExtras(ii);
                setResult(44,i);
                finish();
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("img",R.drawable.d);
                sharedPreferences.edit();
                editor.commit();
                ii.putInt("img",R.drawable.d);
                i.putExtras(ii);
                setResult(44,i);
                finish();
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("img",R.drawable.e);
                sharedPreferences.edit();
                editor.commit();
                ii.putInt("img",R.drawable.e);
                i.putExtras(ii);
                setResult(44,i);
                finish();
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("img",R.drawable.f);
                sharedPreferences.edit();
                editor.commit();
                ii.putInt("img",R.drawable.f);
                i.putExtras(ii);
                setResult(44,i);
                finish();
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("img",R.drawable.g);
                sharedPreferences.edit();
                editor.commit();
                ii.putInt("img",R.drawable.g);
                i.putExtras(ii);
                setResult(44,i);
                finish();
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("img",R.drawable.h);
                sharedPreferences.edit();
                editor.commit();
                ii.putInt("img",R.drawable.h);
                setResult(44,i);
                finish();
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("img",R.drawable.i);
                sharedPreferences.edit();
                editor.commit();
                ii.putInt("img",R.drawable.i);
                i.putExtras(ii);
                setResult(44,i);
                finish();
            }
        });

    }
}
