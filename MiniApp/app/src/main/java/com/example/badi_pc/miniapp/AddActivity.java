package com.example.badi_pc.miniapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.badi_pc.miniapp.data.Contacter;
import com.example.badi_pc.miniapp.data.ContacterDAO;

public class AddActivity extends AppCompatActivity {

    ImageView addContact;
    Contacter contacter;
    EditText nom,prenom,phoneNumber,mail,siteweb,Latitude,Longitude;
    ImageView img;
    ContacterDAO contacterDAO;
    Boolean ok;
    Button btnMap;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    Toast.makeText(AddActivity.this, "Contacter", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    Toast.makeText(AddActivity.this, "Add Contacter", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                    Toast.makeText(AddActivity.this, "Maps", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().hide();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        contacterDAO=new ContacterDAO(getApplicationContext());

        final SharedPreferences sharedPreferences=getSharedPreferences("pass",MODE_PRIVATE);
        btnMap =(Button)findViewById(R.id.btnMAP);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLogged", true);
                sharedPreferences.edit();
                editor.commit();
                Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                Bundle bundle =new Bundle();
               // bundle.putDouble("latitude",0);
                bundle.putInt("ok",1);
                intent.putExtras(bundle);
                startActivityForResult(intent,22);
            }
        });
        nom=(EditText)findViewById(R.id.txtNom00);
        prenom=(EditText)findViewById(R.id.txtPrenom00);
        phoneNumber=(EditText)findViewById(R.id.txtphone00);
        mail=(EditText)findViewById(R.id.txtEmail00);
        siteweb=(EditText)findViewById(R.id.txtSite00);
        Latitude=(EditText)findViewById(R.id.txtLatitude00);
        Longitude=(EditText)findViewById(R.id.txtLongitude00);
        img=(ImageView)findViewById(R.id.txtImage00);
        final int Tests =sharedPreferences.getInt("img",R.drawable.c);
        img.setImageResource(Tests);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ListeImgActivity.class);
                startActivityForResult(intent,44);
            }
        });
        addContact=(ImageView) findViewById(R.id.btnAdd);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(AddActivity.this,Main2Activity.class));
                Toast.makeText(AddActivity.this, nom.getText().toString()+"", Toast.LENGTH_SHORT).show();
                try {
                    contacter=new Contacter();
                    contacter.setNom(nom.getText().toString()+"");
                    contacter.setPrenom(prenom.getText().toString()+"");
                    contacter.setPhone(phoneNumber.getText().toString()+"");
                    contacter.setEmail(mail.getText().toString()+"");
                    contacter.setSiteWeb(siteweb.getText().toString());
                    contacter.setMapLongitude(Longitude.getText().toString()+"");
                    contacter.setMapLatitude(Latitude.getText().toString()+"");
                    contacter.setImg(String.valueOf(Tests));
                }catch (Exception e){
                    contacter.setNom("aa");
                    contacter.setPrenom("aa");
                    contacter.setPhone("aa");
                    contacter.setEmail("aa");
                    contacter.setSiteWeb("aa");
                    contacter.setMapLongitude("aa");
                    contacter.setMapLatitude("aa");
                }

                if(nom.getText().equals("")&&prenom.getText().equals("")&&phoneNumber.getText().equals(""))
                {
                    Toast.makeText(AddActivity.this, "ERRER", Toast.LENGTH_SHORT).show();
                }else {
                    ok=contacterDAO.add(contacter);
                    if (ok){
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Add ERRER !", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
       /* addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*
                //p.setImg();*/
               /* Contacter c =new Contacter();

                c.setNom("badi");
                c.setPrenom("Abdelkalak");
                c.setPhone("20431503");
                c.setEmail("badi@gmail.com");
                c.setSiteWeb("www.google.com");
                c.setMapLatitude("341.222222");
                c.setMapLongitude("32.333669");*/
               // ok=contacterDAO.add(contacter);
        /*
                if (ok){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Add ERRER !", Toast.LENGTH_SHORT).show();
                }

            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==22 && resultCode==2){
            Bundle bundle=data.getExtras();
            Latitude.setText(bundle.getString("a")+"");
            Longitude.setText(bundle.getString("o")+"");
        }
        if (requestCode==44 && resultCode==44){
            TextView tt=(TextView)findViewById(R.id.textView22);
            Bundle bundle =data.getExtras();
            Toast.makeText(getApplicationContext(), bundle.getInt("img"), Toast.LENGTH_SHORT).show();
            img.setImageResource(bundle.getInt("img"));
            tt.setText(bundle.getInt("img")+"");
        }
    }
}
