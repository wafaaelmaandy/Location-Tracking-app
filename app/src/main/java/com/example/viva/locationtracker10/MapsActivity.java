package com.example.viva.locationtracker10;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    //location manager that mange the location
    LocationManager locationManager;
    String locationLink ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        DBConnections dbConnections = new DBConnections(getApplicationContext());
        dbConnections.insertRowLocation("fack location");


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // initialize this location
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        // check network provider is enable
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // parameters provider - min time to undated -min distance to update - location listener
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    // get Latitude
                    double    latitude = location.getLatitude();
                    // get longitude
                    double     longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    // instance goe vorder class
                    Geocoder geocoder = new Geocoder(getApplicationContext());


                    try {


                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality() + ",";
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 50.2f));

                         locationLink = " this is my location : "+ "https://www.google.co.id/maps/@"+latitude+","+longitude ;
                        DBConnections dbConnections = new DBConnections(getApplicationContext());
                        dbConnections.updateLocation(locationLink);
                       //  sendMassage(longitude,latitude);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    // get Latitude
                    double   latitude = location.getLatitude();
                    // get longitude
                    double     longitude = location.getLongitude();
                    // instance the class , latlng
                    LatLng latLng = new LatLng(latitude, longitude);
                    // instance geocoder class
                    Geocoder geocoder = new Geocoder(getApplicationContext());

                    try {

                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality() + ",";
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 50.2f));

                         locationLink = " this is my location : "+ "https://www.google.co.id/maps/@"+latitude+","+longitude ;
                        DBConnections dbConnections = new DBConnections(getApplicationContext());
                        dbConnections.updateLocation(locationLink);
                        // sendMassage(longitude,latitude);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });

        }

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }

    /*---------------------------- */
// send massage
    public void sendMassage(double longitude, double latitude) {
        DBConnections dbConnections = new DBConnections(getApplicationContext());
        String n1 = dbConnections.getSender() ;
        // send massage automatic
        String text = "help me this is my location : "+ "https://www.google.co.id/maps/@"+latitude+","+longitude ;
        SmsManager smsMgr1 = SmsManager.getDefault();
        smsMgr1.sendTextMessage(n1, null, text, null, null);

    }

    public void opensecurity(View view) {
        Intent i = new Intent(getApplicationContext(),SecurityCode.class);
        startActivity(i);
    }
}
