package com.example.pratiksha.lifecare_20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//class for patient donor sign up btton
public class DonorPatient extends AppCompatActivity implements View.OnClickListener{
    protected Button donor,patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_patient);
        donor=(Button)findViewById(R.id.donor_bt);
        patient=(Button)findViewById(R.id.patient_bt);

        donor.setOnClickListener(this);
        patient.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.donor_bt)
        {
            Intent i=new Intent(getApplicationContext(),DonorSignUp.class);
            startActivity(i);
            finish();

        }
        if(view.getId()==R.id.patient_bt)
        {
            Intent i=new Intent(getApplicationContext(),PatientSignUp.class);
            startActivity(i);
            finish();
        }
    }
}
