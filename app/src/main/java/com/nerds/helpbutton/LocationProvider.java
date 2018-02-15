package com.nerds.helpbutton;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ramazan on 2/15/18.
 */

public class LocationProvider {
    private Context ctx;
    private Location location;
    private double longitude,latitude;
    public LocationProvider(Context context) {
        ctx = context;
        LocationManager locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast toast=new Toast(ctx);
            toast.setText("Konum sağlanamıyor...");
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER,locationListener,null);
        if(location==null)
            locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER,locationListener,null);

    }
    public double getLatitude(){
        return location.getLatitude();
    }
    public double getLongitude(){
        return location.getLongitude();
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }

        public void onStatusChanged(String s, int i, Bundle bundle) {}
        public void onProviderEnabled(String s) {}
        public void onProviderDisabled(String s) {}
    };
}
