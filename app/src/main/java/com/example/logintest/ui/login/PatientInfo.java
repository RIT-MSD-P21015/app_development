package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

public class PatientInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        Button startSurveyButton = (Button) findViewById(R.id.button_take_survey);
        Button seePatientDataButton = (Button) findViewById(R.id.button_see_data);

        Button returnDashboardButton = findViewById(R.id.button_return_main_patient_info);

        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());
        startSurveyButton.setOnClickListener(v -> openPatientSurveyActivity());
        seePatientDataButton.setOnClickListener(v -> openPatientDataActivity());
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