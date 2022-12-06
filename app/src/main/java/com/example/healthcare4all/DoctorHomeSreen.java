package com.example.healthcare4all;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoctorHomeSreen extends AppCompatActivity {
    //creates the prescribe and make appointment buttons
    Button prescribe;
    Button makeAppointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_sreen);

        prescribe = findViewById(R.id.prescribe);
        makeAppointment = findViewById(R.id.makeAppointment);

        //When clicking on prescribe it redirects to the enter prescription page.
        prescribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EnterPrescription.class));
            }
        });

        //When clicking on create appointment it redirects to enter appointment page.
        makeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EnterAppointment.class));
            }
        });
    }



}