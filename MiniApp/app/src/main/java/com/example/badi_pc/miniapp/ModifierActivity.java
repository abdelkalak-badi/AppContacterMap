package com.example.badi_pc.miniapp;

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
import android.widget.Toast;

import com.example.badi_pc.miniapp.data.Contacter;
import com.example.badi_pc.miniapp.data.ContacterDAO;

public class ModifierActivity extends AppCompatActivity {

    Contacter con;
    ImageView addContact;
    Contacter contacter;
    EditText nom,prenom,phoneNumber,mail,siteweb,Latitude,Longitude;
    ImageView img;
    ContacterDAO contacterDAO;
    Boolean ok;
    Intent intent;
    Button btnMod;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    Toast.makeText(getApplicationContext(), "Contacter", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(getApplicationContext(),AddActivity.class));
                    Toast.makeText(getApplicationContext(), "Add Contacter", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                    Toast.makeText(getApplicationContext(), "Maps", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);
        contacterDAO=new ContacterDAO(getApplicationContext());
        intent=getIntent();

        final SharedPreferences sharedPreferences=getSharedPreferences("pass",MODE_PRIVATE);

        getSupportActionBar().hide();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        nom=(EditText)findViewById(R.id.txtNom00);
        prenom=(EditText)findViewById(R.id.txtPrenom00);
        phoneNumber=(EditText)findViewById(R.id.txtphone00);
        mail=(EditText)findViewById(R.id.txtEmail00);
        siteweb=(EditText)findViewById(R.id.txtSite00);
        Latitude=(EditText)findViewById(R.id.txtLatitude00);
        Longitude=(EditText)findViewById(R.id.txtLongitude00);
        img=(ImageView)findViewById(R.id.txtImage00);


        Bundle bundle=intent.getExtras();
        con= (Contacter) bundle.getSerializable("c");

        nom.setText(con.getNom()+"");
        prenom.setText(con.getPrenom()+"");
        phoneNumber.setText(con.getPhone()+"");
        mail.setText(con.getEmail()+"");
        siteweb.setText(con.getSiteWeb()+"");
        Latitude.setText(con.getMapLatitude()+"");
        Longitude.setText(con.getMapLongitude()+"");
        img.setImageResource(Integer.parseInt(con.getImg()));
        final int Tests =sharedPreferences.getInt("img",Integer.parseInt(con.getImg()));
        img.setImageResource(Tests);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ListeImgActivity.class);
                startActivityForResult(intent,44);
            }
        });

        btnMod=(Button) findViewById(R.id.btnMode);
        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  startActivity(new Intent(AddActivity.this,Main2Activity.class));
                Toast.makeText(ModifierActivity.this, nom.getText().toString()+"", Toast.LENGTH_SHORT).show();
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

                }


                contacterDAO.delete(con);
                ok=contacterDAO.add(contacter);
                if (ok){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Add ERRER !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
