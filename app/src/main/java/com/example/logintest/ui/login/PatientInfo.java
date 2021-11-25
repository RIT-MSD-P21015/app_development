package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

public class PatientInfo extends AppCompatActivity {

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        Bundle extras = getIntent().getExtras();
        token = extras.getString("token");

        // Grab all the stuff on screen
        Button startSurveyButton = findViewById(R.id.button_take_survey);
        Button seePatientDataButton = findViewById(R.id.button_see_data);
        Button returnDashboardButton = findViewById(R.id.button_return_main_patient_info);
        TextView textViewPatientInfo = findViewById(R.id.textViewPatientInfo);

        // Set size of textViews
        textViewPatientInfo.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        startSurveyButton.setTextSize(SettingsStyle.getFontSize());
        seePatientDataButton.setTextSize(SettingsStyle.getFontSize());
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());
        startSurveyButton.setOnClickListener(v -> openPatientSurveyActivity());
        seePatientDataButton.setOnClickListener(v -> openPatientDataActivity());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Grab all the stuff on screen
        Button startSurveyButton = findViewById(R.id.button_take_survey);
        Button seePatientDataButton = findViewById(R.id.button_see_data);
        Button returnDashboardButton = findViewById(R.id.button_return_main_patient_info);
        TextView textViewPatientInfo = findViewById(R.id.textViewPatientInfo);

        // Set size of textViews
        textViewPatientInfo.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        startSurveyButton.setTextSize(SettingsStyle.getFontSize());
        seePatientDataButton.setTextSize(SettingsStyle.getFontSize());
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());
    }

    private void openPatientSurveyActivity() {
        Intent intent = new Intent(this, PatientSurvey.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    // move to the PatientTest activity page...start the tests
    private void openPatientDataActivity() {
        Intent intent = new Intent(this, PatientData.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        intent.putExtra("token", token);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }
}