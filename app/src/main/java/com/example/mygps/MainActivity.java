package com.example.mygps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private TextView GPSLabel;


    @Override
    protected void onResume() {
        super.onResume();
        LocationManager m = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "Brak uprawnien do pobrania lokalizacji gps, wejdz w ustawienia i nadaj uprawnienia aplikacji", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            m.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0.0F, this);

            Location location = m.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            this.onLocationChanged(location);

            Toast.makeText(this, "znamy lokalizacje", Toast.LENGTH_LONG).show();


        } catch (Exception e) {
            Toast.makeText(this, "BLAD" + e.getMessage(), Toast.LENGTH_LONG).show();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.GPSLabel = findViewById(R.id.GPSLabel);
        this.GPSLabel.setTextSize(20);

    }

    @Override
    public void onLocationChanged(Location location) {

        double longitude = location.getLongitude();
        double latitude=location.getLatitude();

        this.GPSLabel.setText(String.format("(%.2f , %.2f)",longitude,latitude));


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

        Toast.makeText(this, "provider enabled", Toast.LENGTH_LONG).show();

    }
}
