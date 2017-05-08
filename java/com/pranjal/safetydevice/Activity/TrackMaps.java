package com.pranjal.safetydevice.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pranjal.safetydevice.API.Response.LocationOfUserIndividualResponse;
import com.pranjal.safetydevice.API.Service.LocationOfUsersService;
import com.pranjal.safetydevice.R;
import com.pranjal.safetydevice.Utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrackMaps extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        APICall();
    }

    private void APICall() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocationOfUsersService service = retrofit.create(LocationOfUsersService.class);

        service.location().enqueue(new Callback<List<LocationOfUserIndividualResponse>>() {
            @Override
            public void onResponse(Call<List<LocationOfUserIndividualResponse>> call, Response<List<LocationOfUserIndividualResponse>> response) {
//                Toast.makeText(TrackMaps.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<LocationOfUserIndividualResponse>> call, Throwable t) {
//                Toast.makeText(TrackMaps.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public   void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng a = new LatLng(26.449895, 74.639916);
        Marker aa = mMap.addMarker(new MarkerOptions()
                .position(a)
                .title("Ujjwal"));
//        aa.showInfoWindow();

        LatLng b = new LatLng(28.613939, 77.209021);
        Marker bb = mMap.addMarker(new MarkerOptions()
                .position(b)
                .title("Abhishek"));
//        bb.showInfoWindow();

        LatLng c = new LatLng(26.912434, 75.787271);
        Marker cc = mMap.addMarker(new MarkerOptions()
                .position(c)
                .title("Pranjal"));
//        cc.showInfoWindow();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(26.238947, 73.024309))
                .title("My Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
        );

        CameraPosition camPos = new CameraPosition.Builder()
                .target(new LatLng(26.238947, 73.024309))
                .zoom(6)
                .tilt(70)
                .build();
        CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
        googleMap.animateCamera(camUpd3);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(TrackMaps.this, "Error", Toast.LENGTH_SHORT).show();
        String phone = "7728824332";
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(TrackMaps.this, "Error", Toast.LENGTH_SHORT).show();
        String phone = "7728824332";
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        startActivity(callIntent);
        return true;
    }
}
