package com.assessment.paulkim.codeauthassess;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG = 9001;

    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (versionCheck()) {
            init();
        }
        sendLngLat();

        imageLink();
    }


    // if versionCheck is passed then we can use button to navigate to the maps
    private void init() {
        Button CurrentLocation = findViewById(R.id.location_button);
        CurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

    }

    //get button reference and get coordinates through edit text
    private void sendLngLat() {
        Button coordinates = findViewById(R.id.btn_show);
        coordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double lat = 0;
                double lng = 0;

                EditText etLat = findViewById(R.id.et_lat);

                if (etLat.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please enter a latitude", Toast.LENGTH_SHORT).show();
                    etLat.requestFocus();
                    return;
                } else {
                    //getting user input
                    lat = Double.parseDouble(etLat.getText().toString());
                }

                EditText etLng = findViewById(R.id.et_lng);

                if (etLng.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please enter a longitude", Toast.LENGTH_SHORT).show();
                    etLng.requestFocus();
                    return;
                } else {
                    //getting user input
                    lng = Double.parseDouble(etLng.getText().toString());
                }
                //intent
                Intent intent = new Intent(MainActivity.this, CoordinateActivity.class);
                intent.putExtra("lat", lat);
                intent.putExtra("lng", lng);

                startActivity(intent);
            }
        });

    }

    //send url to activity to show image
    private void imageLink() {
        Button showPic = findViewById(R.id.image_button);
        showPic.setOnClickListener(new View.OnClickListener() {

            EditText urlString = findViewById(R.id.url_pic);

            @Override
            public void onClick(View view) {

                if (urlString.getText().toString().isEmpty()) {

                    Toast.makeText(getBaseContext(), "Please enter a valid URL", Toast.LENGTH_SHORT).show();

                } else {
                    value = urlString.getText().toString();
                    Intent urlLink = new Intent(MainActivity.this, ImageActivity.class);
                    urlLink.putExtra("url_of_pic", value);
                    startActivity(urlLink);
                }

            }
        });
    }

    //checking the version for google play services
    public boolean versionCheck() {
        Log.d(TAG, "versionCheck: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            Log.d(TAG, "versionCheck: google play services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "versionCheck: google play services can be fixed");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG);
            dialog.show();
        } else {
            Toast.makeText(this, "You cannot make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
