package com.example.pratiksha.lifecare_20;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RequestBlood extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{

    public Button search, detail;
    EditText bldgrp;
    public static String bloodsearch;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood);

        detail=(Button)findViewById(R.id.donordeatail_bt);

        bldgrp=(EditText)findViewById(R.id.search_et);
        detail.setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {


        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.donormenu, menu);
        invalidateOptionsMenu();


        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId())
        {

            case R.id.signout_donor:
            {


                SharedPreferences sharedPreferences=getSharedPreferences("user_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                DBHelper db=new DBHelper(this);
                boolean flag=db.updateData("",0);

                editor.putString("username_str","N/A");
                editor.putString("password_str","N/A");
                editor.putString("type","N/A");
                editor.commit();

                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
            break;



        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onClick(View view) {

         if(view.getId()==R.id.donordeatail_bt)
        {
            Intent i=new Intent(this.getApplicationContext(),DonarList.class);

            i.putExtra("blood_group",bldgrp.getText().toString());
            startActivityForResult(i,1);}

        }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
