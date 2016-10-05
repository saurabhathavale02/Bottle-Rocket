package com.example.saurabh.storedatadisplay.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.saurabh.storedatadisplay.R;
import com.example.saurabh.storedatadisplay.model.pojo.Store;
import com.example.saurabh.storedatadisplay.ui.interfaces.DisplayContentView;

/**
 * Created by saurabh on 10/02/16.
 */

public class DisplayContent extends AppCompatActivity implements DisplayContentView {
    TextView Name, Address, CityName,PhoneNumber,Latitude,Longitude,State;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaycontent);

        Name = (TextView) findViewById(R.id.name);
        Address = (TextView) findViewById(R.id.address);
        CityName = (TextView) findViewById(R.id.cityname);
        PhoneNumber = (TextView) findViewById(R.id.phonenumber);
        Latitude = (TextView) findViewById(R.id.latitude);
        Longitude = (TextView) findViewById(R.id.longitude);
        State=(TextView) findViewById(R.id.state);

        Intent intent = this.getIntent();

        Store selectedstore= (Store) intent.getSerializableExtra("selectedstore");

        Name.setText(selectedstore.getName());
        Address.setText(selectedstore.getAddress());
        CityName.setText(selectedstore.getCity());
        PhoneNumber.setText(selectedstore.getPhone());
        Latitude.setText(selectedstore.getLatitude());
        Longitude.setText(selectedstore.getLongitude());
        State.setText(selectedstore.getState());
    }
}