package com.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;
    public String apiBaseUrl = "https://api.weather.gov/";
    public String gridPoints;
    public TextView locationTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // set up variables
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationTextBox = findViewById(R.id.locationTextBox);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // check if location is enabled for app
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled(LocationManager.FUSED_PROVIDER)){
            //if not, request permission
            String[] permissionString = new String [] {Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(this, permissionString, 44);
        }
        // get location by default (actually, might be better do do via button)
        // getLocation();
    }
    // location getter
    @SuppressLint("MissingPermission")
    public void getLocation(View view){ // view
        try {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                // location to basic text box
                                locationTextBox.setText(location.toString());
                            }
                        }
                    });
        } catch(Exception e){
            // what are the odds something would go wrong?
            locationTextBox.setText("oopsies");
        }
    }
    //
    public void getGridPoints(){
        //
    }
    // 
    public void getWeatherForecast(){
        //
    }
}