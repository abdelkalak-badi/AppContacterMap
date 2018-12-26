package com.example.badi_pc.miniapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.badi_pc.miniapp.data.Contacter;
import com.example.badi_pc.miniapp.data.ContacterDAO;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    AutoCompleteTextView edit;
    List<String> liste;
    ListView myliste;
    ArrayList<Contacter> MyArray;
    MyAdapter myadapter;
    ContacterDAO contacterDAO;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(HomeActivity.this, "Contacter", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(getApplicationContext(), AddActivity.class));
                    Toast.makeText(HomeActivity.this, "Add Contacter", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                    Toast.makeText(HomeActivity.this, "Maps", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        MyArray = new ArrayList<Contacter>();

        contacterDAO = new ContacterDAO(getApplicationContext());
        try {
            MyArray.addAll(contacterDAO.findAll());
        }catch (Exception e){
            startActivity(new Intent(getApplicationContext(),AddActivity.class));
        }

        edit= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        liste=new ArrayList<String>();
        for (Contacter cont :MyArray){
            liste.add(cont.getNom());
            liste.add(cont.getPrenom());
            liste.add(cont.getPhone());
        }

        edit.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,liste));

        myadapter = new MyAdapter(getApplicationContext(), R.layout.cellule, MyArray);
        myliste = (ListView) findViewById(R.id.myliste);
        myliste.setAdapter(myadapter);
        myliste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                TextView txt = (TextView) view.findViewById(R.id.textNom);

                PopupMenu popupMenu = new PopupMenu(HomeActivity.this, txt);
                popupMenu.getMenuInflater().inflate(R.menu.menu_contextuel, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.i0) {
                            Contacter c =MyArray.get(position);
                            String myData = "tel:"+c.getPhone();
                            Intent myActivity = new Intent(Intent.ACTION_DIAL, Uri.parse(myData));
                            startActivity(myActivity);
                            Toast.makeText(HomeActivity.this, "I0", Toast.LENGTH_SHORT).show();
                        }
                        if (item.getItemId()==R.id.i1){
                            Contacter c =MyArray.get(position);
                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                    "mailto",c.getEmail(), null));
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello");
                            startActivity(Intent.createChooser(emailIntent, "Send email..."));
                            Toast.makeText(HomeActivity.this, "I1", Toast.LENGTH_SHORT).show();
                        }
                        if (item.getItemId()==R.id.i2){

                            ContacterDAO cd =new ContacterDAO(getApplicationContext());
                            Contacter c =MyArray.get(position);
                            cd.delete(c);
                            MyArray.clear();
                            MyArray.addAll(contacterDAO.findAll());
                            myadapter = new MyAdapter(getApplicationContext(), R.layout.cellule, MyArray);
                            myliste.setAdapter(myadapter);
                            Toast.makeText(HomeActivity.this, "Suprition Terminer", Toast.LENGTH_SHORT).show();
                        }
                        if (item.getItemId()==R.id.i3){
                            ContacterDAO cd =new ContacterDAO(getApplicationContext());
                            Contacter c =MyArray.get(position);
                            Intent intent=new Intent(getApplicationContext(),ModifierActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("c",c);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.navigation_home){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }
        if(item.getItemId()==R.id.navigation_dashboard){
            startActivity(new Intent(getApplicationContext(),AddActivity.class));
        }
        if(item.getItemId()==R.id.navigation_notifications){
            startActivity(new Intent(getApplicationContext(),MapsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
    /*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextuel,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.i0:
                Toast.makeText(this, "test ok;;;;;;;;;", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }*/
}
