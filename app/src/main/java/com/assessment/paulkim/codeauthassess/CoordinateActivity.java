package com.assessment.paulkim.codeauthassess;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CoordinateActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(CoordinateActivity.this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        latLngcoordinates();

    }

    public LatLng latLngcoordinates() {
        //12. receiving lat and long from mainActivity
        double latitude = getIntent().getDoubleExtra("lat", 0);

        double longitude = getIntent().getDoubleExtra("lng", 0);

        LatLng position = new LatLng(latitude, longitude);

        MarkerOptions options = new MarkerOptions();

        options.position(position);
        options.title("Position");
        options.snippet("Latitude:" + latitude + " Longitude:" + longitude);

        mMap.addMarker(options);

        CameraUpdate updatePositions = CameraUpdateFactory.newLatLng(position);

        CameraUpdate updateZoom = CameraUpdateFactory.zoomTo(15);

        if (mMap != null) {
            mMap.moveCamera(updatePositions);

            mMap.animateCamera(updateZoom);
        }
        return position;
    }
}
