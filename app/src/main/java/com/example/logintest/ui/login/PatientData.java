package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

public class PatientData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data);

        // Grab all the stuff on screen
        Button returnDashboardButton = findViewById(R.id.button_return_main_patient_data);
        TextView textViewPatientData = findViewById(R.id.textViewPatientData);
        TextView textViewTodo = findViewById(R.id.textViewToDo);

        // Set size of textViews
        textViewPatientData.setTextSize(SettingsStyle.getFontSize());
        textViewTodo.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Grab all the stuff on screen
        Button returnDashboardButton = findViewById(R.id.button_return_main_patient_data);
        TextView textViewPatientData = findViewById(R.id.textViewPatientData);
        TextView textViewTodo = findViewById(R.id.textViewToDo);

        // Set size of textViews
        textViewPatientData.setTextSize(SettingsStyle.getFontSize());
        textViewTodo.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());
    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }

}