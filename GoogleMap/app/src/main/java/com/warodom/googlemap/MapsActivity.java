package com.warodom.googlemap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    private Marker mCoe;
    private Marker mScb;
    private Marker mCentral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // More details: https://developers.google.com/maps/documentation/android-api/marker
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng coeLatLng = new LatLng(7.893450, 98.352476);
        mCoe  = mMap.addMarker(new MarkerOptions()
                .position(coeLatLng)
                .snippet("Ccomputer Engineering Department, PSU Phuket")
                .title("CoE, Phuket Campus"));
        mCoe.setTag(0);

        mScb = mMap.addMarker(new MarkerOptions().position(
                new LatLng(7.8965542,98.3551417))
                .snippet("SCB Office description")
                .icon(BitmapDescriptorFactory      // replace default icon marker
                        .fromResource(R.mipmap.ic_launcher_round))
                .title("SCB PSU Phuket Office"));
        mScb.setTag(0);

        mCentral = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(7.891670, 98.368040))
                .snippet("Central Office description")
                .icon(BitmapDescriptorFactory   // replace icon color
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .alpha(0.7f)                    // opacity
                .title("Central Festival Phuket"));
        mCentral.setTag(0);
        mCentral.setDraggable(true);

        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(coeLatLng).zoom(14).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        Toast.makeText(this, marker.getTitle() + " is starting dragged " , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        System.out.println( marker.getTitle() + " is dragged " );
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        LatLng position = marker.getPosition();
        Toast.makeText(this, marker.getTitle() + " is ending dragged at " + position , Toast.LENGTH_SHORT).show();
    }
}
