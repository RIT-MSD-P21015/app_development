package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

public class Settings extends AppCompatActivity {

    private String token;
    private static Boolean textToSpeech = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Bundle extras = getIntent().getExtras();
        token = extras.getString("token");

        // Grab all the stuff on screen
        Button aboutButton = findViewById(R.id.button_about);
        Button styleButton = findViewById(R.id.button_style);
        Button clearDataButton = findViewById(R.id.button_clear_data);
        Button returnDashboardButton = findViewById(R.id.button_return_main_settings);
        TextView textViewSettings = findViewById(R.id.textViewSettings);
        Switch textToSpeechSwitch = findViewById(R.id.textToSpeechSwitch);

        // Get value of textToSpeech Switch
        textToSpeech = textToSpeechSwitch.isChecked();

        // Set size of textViews
        textViewSettings.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        aboutButton.setTextSize(SettingsStyle.getFontSize());
        styleButton.setTextSize(SettingsStyle.getFontSize());
        clearDataButton.setTextSize(SettingsStyle.getFontSize());
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());
        textToSpeechSwitch.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        aboutButton.setOnClickListener(v -> openAboutActivity());
        styleButton.setOnClickListener(v -> openStyleActivity());
        clearDataButton.setOnClickListener(v -> confirmClear());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Grab all the stuff on screen
        Button aboutButton = findViewById(R.id.button_about);
        Button styleButton = findViewById(R.id.button_style);
        Button clearDataButton = findViewById(R.id.button_clear_data);
        Button returnDashboardButton = findViewById(R.id.button_return_main_settings);
        TextView textViewSettings = findViewById(R.id.textViewSettings);

        // Set size of textViews
        textViewSettings.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        aboutButton.setTextSize(SettingsStyle.getFontSize());
        styleButton.setTextSize(SettingsStyle.getFontSize());
        clearDataButton.setTextSize(SettingsStyle.getFontSize());
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());
    }

    private void openAboutActivity() {
        Intent intent = new Intent(this, SettingsAbout.class);
        startActivity(intent);
    }

    private void openStyleActivity() {
        Intent intent = new Intent(this, SettingsStyle.class);
        startActivity(intent);
    }

    private void confirmClear() {

        // TODO: Clear patient data...reset app to default
        // TODO: Fix the pop-up button
        new AlertDialog.Builder(this)
                .setTitle("Clear Patient Info")
                .setMessage("Are you sure you want to clear all patient info?")
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", null)
                .show();

//        Intent intent = new Intent(this, Dashboard.class);
//        startActivity(intent);
//        finish();
    }



    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        intent.putExtra("token", token);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }

    public static Boolean getTextToSpeechBool() {
        return textToSpeech;
    }
}