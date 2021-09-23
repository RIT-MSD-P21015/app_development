package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button patientButton = (Button) findViewById(R.id.button_patient_info);
        Button physicalTestButton = (Button) findViewById(R.id.button_physical_test);
        Button resultButton = (Button) findViewById(R.id.button_results);
        Button settingsButton = (Button) findViewById(R.id.button_settings);
        Button logoutButton = (Button) findViewById(R.id.button_logout);


        patientButton.setOnClickListener(v -> openActivityPatientInfo());

        physicalTestButton.setOnClickListener(v -> openActivityPhysicalTest());

        resultButton.setOnClickListener(v -> openActivityResults());

        settingsButton.setOnClickListener(v -> openActivitySettings());

        logoutButton.setOnClickListener(v -> logout());

    }

    @Override
    public void onBackPressed() {
        // do nothing
    }

    public void openActivityPatientInfo() {
        Intent intent = new Intent(this, PatientInfo.class);
        startActivity(intent);
    }

    public void openActivityPhysicalTest() {
        Intent intent = new Intent(this, PhysicalTest.class);
        startActivity(intent);
    }

    public void openActivityResults() {
        Intent intent = new Intent(this, Results.class);
        startActivity(intent);
    }

    public void openActivitySettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }





}