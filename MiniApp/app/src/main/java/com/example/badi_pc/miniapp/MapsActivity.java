package com.example.badi_pc.miniapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.badi_pc.miniapp.data.Contacter;
import com.example.badi_pc.miniapp.data.ContacterDAO;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        intent=getIntent();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ContacterDAO contacterDAO = new ContacterDAO(getApplicationContext());
        List<Contacter> contacters = new ArrayList<Contacter>();
        try {
            contacters = contacterDAO.findAll();
        }catch (Exception e){

        }

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.getUiSettings().setZoomControlsEnabled(true);
       // if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        //    return;
       // }
        final SharedPreferences sharedPreferences=getSharedPreferences("pass",MODE_PRIVATE);
        final Boolean Tests =sharedPreferences.getBoolean("isLogged",false);
       mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
           @Override
           public void onMapClick(LatLng latLng) {
               if (Tests){
                   SharedPreferences.Editor editor = sharedPreferences.edit();
                   editor.putBoolean("isLogged", false);
                   sharedPreferences.edit();
                   editor.commit();
               Toast.makeText(MapsActivity.this, latLng.latitude+" - "+latLng.longitude, Toast.LENGTH_SHORT).show();
               Bundle bundle =intent.getExtras();
                   bundle.putInt("ok",0);
                   bundle.putString("a",String.valueOf(latLng.latitude));
                   bundle.putString("o",String.valueOf(latLng.longitude));
                   intent.putExtras(bundle);
                   setResult(2,intent);
                   finish();
               }

           }
       });

       // mMap.setBuildingsEnabled(true);
        if (contacters!=null){
            for(Contacter con:contacters){
                try {
                    LatLng sydney2 = new LatLng(Double.parseDouble(con.getMapLatitude()), Double.parseDouble(con.getMapLongitude()));
                    mMap.addMarker(new MarkerOptions().position(sydney2).title(con.getNom()+" "+con.getPrenom()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney2,12));
                }catch (Exception e){

                }

            }
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(36.46731366129069, 10.739675505617702);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Badi ABDELKALAK").snippet("Click To").icon(BitmapDescriptorFactory.fromResource(R.drawable.callcenterm)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
