package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

public class PatientInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

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

        // Set color of button backgrounds
        startSurveyButton.setBackgroundColor(SettingsStyle.getPrimaryColor());
        seePatientDataButton.setBackgroundColor(SettingsStyle.getSecondaryColor());
        returnDashboardButton.setBackgroundColor(SettingsStyle.getPrimaryColor());

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
        startActivity(intent);
    }

    // move to the PatientTest activity page...start the tests
    private void openPatientDataActivity() {
        Intent intent = new Intent(this, PatientData.class);
        startActivity(intent);
    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }
}