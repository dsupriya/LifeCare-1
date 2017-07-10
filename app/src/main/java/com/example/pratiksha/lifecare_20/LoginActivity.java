package com.example.pratiksha.lifecare_20;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.LinkedList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, LocationListener {

    public static Context context;
    String tp;
    Button login_bt, signup_bt;
    EditText loginusername_et, loginpassword_et;
    Spinner select_sp;
    public static String loginusername_str, loginpassword_str;
    LocationManager myManager;
    LocationListener locationListener;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_bt = (Button) findViewById(R.id.login_bt);
        signup_bt = (Button) findViewById(R.id.signup_bt);

        myManager = (LocationManager) getSystemService(context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        myManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000,36000, this);

        loginusername_et=(EditText)findViewById(R.id.loginusername_et);
        loginpassword_et=(EditText)findViewById(R.id.loginpassword_et);
        login_bt.setOnClickListener(this);
        signup_bt.setOnClickListener(this);
        select_sp=(Spinner)findViewById(R.id.select);
        context=this;

        select_sp.setOnItemSelectedListener(this);
        LinkedList list=new LinkedList();
        list.add("Donor");
        list.add("Patient");
        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_sp.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.login_bt)
        {
            loginusername_str=loginusername_et.getText().toString();
            loginpassword_str=loginpassword_et.getText().toString();


                    BackgroundTask backgroundTask = new BackgroundTask(this);
                    String method = "login";
                    backgroundTask.execute(method, loginusername_str, loginpassword_str, tp);

        }
        else if(view.getId()==R.id.signup_bt)
        {
            Intent i=new Intent(getApplicationContext(),DonorPatient.class);
            startActivity(i);

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {


        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.helpmenu, menu);
        invalidateOptionsMenu();


        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId())
        {

            case R.id.help_login:
            {
                startActivity(new Intent(getApplicationContext(),AppHelp.class));
                //finish();
            }
            break;



        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        tp=adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        String s = latitude+" "+longitude;
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();

        DBHelper db=new DBHelper(this);
        Cursor c = db.retrieveData();

        //Only one row is allowed in my sqlite. So check if already present. If not, insert data.
        if (c != null && c.getCount() == 0)
            db.insertData();


        Cursor cursor = db.retrieveData();
        cursor.moveToFirst();

        //Get id of user from database
        String id = cursor.getString(cursor.getColumnIndex("id"));


       if(!id.equals(""))

        { BackgroundTask bg=new BackgroundTask(this);
        String methood="latlon";
        bg.execute(methood,id,""+latitude,""+latitude);
    }}

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
