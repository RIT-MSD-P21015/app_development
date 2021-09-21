package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

public class PatientSurvey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_survey);

        Button submitSurveyButton = findViewById(R.id.button_submit_survey);

        submitSurveyButton.setOnClickListener(v -> openDashboardActivity());
    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }

    //TODO: add scrollable to survey, also make buttons and text bigger

    // Override back button for confirm leave
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Cancel Survey")
                .setMessage("Are you sure you want to cancel survey?")
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", null)
                .show();
    }
}