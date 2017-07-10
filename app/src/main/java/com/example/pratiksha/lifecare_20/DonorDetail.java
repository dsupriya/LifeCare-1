package com.example.pratiksha.lifecare_20;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
//class to implement Detail of every donor
public class DonorDetail extends AppCompatActivity {

    TextView name,blood,address,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_detail);


        name=(TextView)findViewById(R.id.donor_name);
        blood=(TextView)findViewById(R.id.donor_bloodgroup);
        address=(TextView)findViewById(R.id.donor_address);
        contact=(TextView)findViewById(R.id.donor_contact);





        name.setText(BackgroundTask.blglist.get(DonarList.index_donor).getDonarName());
        blood.setText(BackgroundTask.blglist.get(DonarList.index_donor).getDonorblood());
        address.setText(BackgroundTask.blglist.get(DonarList.index_donor).getLatLon());

        contact.setText(BackgroundTask.blglist.get(DonarList.index_donor).getDonorContact());








    }
}
