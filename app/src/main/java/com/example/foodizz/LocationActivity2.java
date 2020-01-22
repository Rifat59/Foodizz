package com.example.foodizz;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.foodizz.services.GpsMubin2;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;

import org.jetbrains.annotations.Nullable;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

public class LocationActivity2 extends FragmentActivity implements OnMapReadyCallback, GpsMubin2.Listeners {

    private static final String TAG = "LocationActivity";

    public static final int REQUEST_RESTAURANT = 101;

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Button find_res_button;

    private GpsMubin2 gpsMubin2;

    private boolean isLocationUpdated = false;


    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        find_res_button = (Button) findViewById(R.id.find_res_button_id);
        find_res_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationActivity2.this,RestaurentTypeActivity.class);

                startActivity(intent);
                //startActivityForResult(intent, REQUEST_RESTAURANT);
            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        gpsMubin2 = new GpsMubin2(this, false);
        gpsMubin2.listeners = this;
        gpsMubin2.onSaveInstanceState(savedInstanceState);
        gpsMubin2.myCustomUpdate();


    }

    @Override
    protected void onResume() {
        super.onResume();
        //gpsMubin2.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gpsMubin2.onPause();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        gpsMubin2.onActivityResult(requestCode, resultCode, intent);
        Log.d(TAG, "onActivityResult: requestCode: " + requestCode);
        Log.d(TAG, "onActivityResult: resultCode: " + resultCode);

        if (requestCode == REQUEST_RESTAURANT && resultCode == -10) {
            // ok
            Restaurent restaurent = intent.getParcelableExtra("myLatLng");
            Log.d(TAG, "onActivityResult: " + restaurent.Name);
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(restaurent.getLatLng()).title(restaurent.Name));
            Log.d(TAG, "onActivityResult: " + restaurent.getLatLng());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(restaurent.getLatLng(), 12f));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        gpsMubin2.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        gpsMubin2.onSaveInstanceState(outState);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);

    }


    // region gpsmubin2 callbacks
    @Override
    public void onRequestingLocationUpdates(boolean isRequestingLocationUpdates) {

    }

    @Override
    public void onCurrentLocationTriggers(@Nullable Location currentLocation, @Nullable String updateTime) {
        Log.d(TAG, "onCurrentLocationTriggers: " + currentLocation);
        //Toast.makeText(this, gpsMubin2.getCompleteAddressString(currentLocation.getLatitude(), currentLocation.getLongitude()), Toast.LENGTH_SHORT).show();
        if (!isLocationUpdated) {
            Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())).title(currentLocation.toString()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 14f));

            isLocationUpdated = true;
        }
    }


    @Override
    public void onFinalLocationTrigger(@Nullable Location currentLocation) {

    }

    @Override
    public void onActivityResultSuccess() {
        gpsMubin2.startUpdatesButtonHandler(null);
    }

    //endregion


}
