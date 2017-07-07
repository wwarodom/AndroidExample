package com.warodom.googlemapinfowindows;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker mCoe;
    private Marker mScb;
    private TextView tvBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvBottom = (TextView) findViewById(R.id.tvBottom);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        CustomInfoWindowAdapter customInfoWindowAdapter =
                new CustomInfoWindowAdapter(getApplicationContext(),tvBottom);
        mMap.setInfoWindowAdapter(customInfoWindowAdapter);
        mMap.setOnInfoWindowClickListener(customInfoWindowAdapter);

        // Add a marker in Sydney and move the camera
        LatLng coeLatLng = new LatLng(7.893450, 98.352476);
        mCoe = mMap.addMarker(new MarkerOptions()
                .position(coeLatLng)
                .snippet("Computer Engineering Department, PSU Phuket")
                .title("CoE, Phuket Campus"));
        mCoe.setTag("CoE");
        mCoe.showInfoWindow();

        mScb = mMap.addMarker(new MarkerOptions().position(
                new LatLng(7.8965542, 98.3551417))
                .snippet("SCB Office description")
                .icon(BitmapDescriptorFactory      // replace default icon marker
                        .fromResource(R.mipmap.ic_launcher_round))
                .title("SCB PSU Phuket Office"));
        mScb.setTag("SCB");

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(coeLatLng).zoom(14).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

}
