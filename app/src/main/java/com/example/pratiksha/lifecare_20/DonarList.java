package com.example.pratiksha.lifecare_20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
//class to show the list of donors After onclick of search button.

public class DonarList extends AppCompatActivity implements  AdapterView.OnItemClickListener{


    public static int index_donor;
    public static ListView donor_lst;
    EditText bldgrp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_list);
        donor_lst=(ListView)findViewById(R.id.donor_listview);
        donor_lst.setOnItemClickListener(this);


        String method="donorlist";
        BackgroundTask bt=new BackgroundTask(this);
        Intent intent2 = getIntent();

        String first = intent2.getStringExtra("blood_group");
        bt.execute(method,first);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        index_donor=i;
        startActivity(new Intent(getApplicationContext(),DonorDetail.class));
    }
}
