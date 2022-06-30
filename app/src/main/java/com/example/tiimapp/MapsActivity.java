package com.example.tiimapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng current = new LatLng(52.25657, 20.89683);
        googleMap.addMarker(new MarkerOptions()
                .position(current)
                .title("Tu jesteś"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(current));

        LatLng field1 = new LatLng(52.25621, 20.89498);
        googleMap.addMarker(new MarkerOptions()
            .position(field1)
            .title("Przedszkole"));

        LatLng field2 = new LatLng(52.25297, 20.89302);
        googleMap.addMarker(new MarkerOptions()
                .position(field2)
                .title("SWF"));

        LatLng field3 = new LatLng(52.26124, 20.90153);
        googleMap.addMarker(new MarkerOptions()
                .position(field3)
                .title("Szkoła"));

        LatLng field4 = new LatLng(52.24985, 20.91353);
        googleMap.addMarker(new MarkerOptions()
                .position(field4)
                .title("Boisko"));

        LatLng field5 = new LatLng(52.26109, 20.91640);
        googleMap.addMarker(new MarkerOptions()
                .position(field5)
                .title("Boisko"));

        LatLng field6 = new LatLng(52.25941, 20.93803);
        googleMap.addMarker(new MarkerOptions()
                .position(field6)
                .title("Boisko piłkarskie Obrońców Tobroku"));

        LatLng field7 = new LatLng(52.25187, 20.94001);
        googleMap.addMarker(new MarkerOptions()
                .position(field2)
                .title("Hala piłkarska"));
    }
}

    