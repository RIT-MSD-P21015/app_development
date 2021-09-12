package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logintest.ui.login.LoginActivity;

public class Dashboard extends AppCompatActivity {
    private Button patientButton, physicalTestButton, resultButton, settingsButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        patientButton = (Button) findViewById(R.id.button_patient_info);
        physicalTestButton = (Button) findViewById(R.id.button_physical_test);
        resultButton = (Button) findViewById(R.id.button_results);
        settingsButton = (Button) findViewById(R.id.button_settings);
        logoutButton = (Button) findViewById(R.id.button_logout);


        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPatientInfo();
            }
        });

        physicalTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPhysicalTest();
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityResults();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySettings();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    @Override
    public void onBackPressed() {
        // do nothing
//        Intent intent = new Intent(this, Dashboard.class);
//        startActivity(intent);
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