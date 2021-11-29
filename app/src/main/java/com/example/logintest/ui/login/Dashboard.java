package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;
import com.example.logintest.data.AppData;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Grab all the stuff on screen
        Button patientButton = findViewById(R.id.button_patient_info);
        Button physicalTestButton = findViewById(R.id.button_physical_test);
        Button resultButton = findViewById(R.id.button_results);
        Button settingsButton = findViewById(R.id.button_settings);
        Button logoutButton = findViewById(R.id.button_logout);
        TextView textViewDashboard = findViewById(R.id.textViewDashboard);

        // Set size of textViews
        textViewDashboard.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        patientButton.setTextSize(SettingsStyle.getFontSize());
        physicalTestButton.setTextSize(SettingsStyle.getFontSize());
        resultButton.setTextSize(SettingsStyle.getFontSize());
        settingsButton.setTextSize(SettingsStyle.getFontSize());
        logoutButton.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        patientButton.setOnClickListener(v -> openActivityPatientInfo());
        physicalTestButton.setOnClickListener(v -> openActivityPhysicalTest());
        resultButton.setOnClickListener(v -> openActivityResults());
        settingsButton.setOnClickListener(v -> openActivitySettings());
        logoutButton.setOnClickListener(v -> logout());

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Grab all the stuff on screen
        Button patientButton = findViewById(R.id.button_patient_info);
        Button physicalTestButton = findViewById(R.id.button_physical_test);
        Button resultButton = findViewById(R.id.button_results);
        Button settingsButton = findViewById(R.id.button_settings);
        Button logoutButton = findViewById(R.id.button_logout);
        TextView textViewDashboard = findViewById(R.id.textViewDashboard);

        // Set size of textViews
        textViewDashboard.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        patientButton.setTextSize(SettingsStyle.getFontSize());
        physicalTestButton.setTextSize(SettingsStyle.getFontSize());
        resultButton.setTextSize(SettingsStyle.getFontSize());
        settingsButton.setTextSize(SettingsStyle.getFontSize());
        logoutButton.setTextSize(SettingsStyle.getFontSize());
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

        // revoke authentication token
        AppData tokenData = (AppData) getApplicationContext();
        tokenData.clearToken();

        this.finish();
    }





}