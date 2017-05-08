package com.pranjal.safetydevice.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pranjal.safetydevice.R;

public class Home extends AppCompatActivity {

    LocationManager lm;
    Location location;
    Double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        populateListView();
        listItemOnClickListener();

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        lat = location.getLatitude();
        lon = location.getLongitude();

    }

    private void populateListView() {
        // creating the list
        String[] items = {"Mom", "Dad", "Brother", "Ujjwal", "Abhishek", "Pranjal"};

        // creating the adaptor which'll store the items in the list
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                this,                   // Context
                R.layout.list_item,     // Where to display the item
                items                   // From where to get the items
        );

        // putting the items onto the list
        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(listAdapter);
    }

    private void listItemOnClickListener() {
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // we have chosen onItemClick because we want to chose wat happens when we click the
            // item in the list & not the complete list

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Call code
                /*
                String phone = "7728824332";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(Home.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
                */

                // Message Code

                SmsManager smsManager = SmsManager.getDefault();
                String smsBody = "Pranjal here. I need help at Latitude : " + lat + ", Longitude : " + lon;
                smsManager.sendTextMessage("+91-8387014808", null, smsBody, null, null);
            }
        });

    }

    public void toMaps(View v){
        Intent i = new Intent(Home.this, TrackMaps.class);
        startActivity(i);
    }
}
