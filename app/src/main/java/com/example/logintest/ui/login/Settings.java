package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;
import com.example.logintest.data.AppData;
import com.example.logintest.data.NetworkManager;

import java.io.IOException;

public class Settings extends AppCompatActivity {

    private static Boolean textToSpeech = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Grab all the stuff on screen
        Button aboutButton = findViewById(R.id.button_about);
        Button styleButton = findViewById(R.id.button_style);
        Button clearDataButton = findViewById(R.id.button_clear_data);
        Button returnDashboardButton = findViewById(R.id.button_return_main_settings);
        TextView textViewSettings = findViewById(R.id.textViewSettings);
        Switch textToSpeechSwitch = findViewById(R.id.textToSpeechSwitch);

        // Get value of textToSpeech Switch
        textToSpeechSwitch.setChecked(textToSpeech);

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
        clearDataButton.setOnClickListener(v -> confirmDelete());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

        // Set color of button backgrounds
        aboutButton.setBackgroundColor(SettingsStyle.getPrimaryColor());
        styleButton.setBackgroundColor(SettingsStyle.getSecondaryColor());
        clearDataButton.setBackgroundColor(SettingsStyle.getPrimaryColor());
        returnDashboardButton.setBackgroundColor(SettingsStyle.getSecondaryColor());

        textToSpeechSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> textToSpeech = isChecked);
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

    private void confirmDelete() {

        // TODO: Clear patient data...reset app to default
        // TODO: Fix the pop-up button
        new AlertDialog.Builder(this)
                .setTitle("Delete User Account")
                .setMessage("Are you sure you want to clear all patient info?")
                .setPositiveButton("Yes", (dialog, which) -> deleteUser())
                .setNegativeButton("No", null)
                .show();
    }

    private void deleteUser() {
        AppData tokenData = (AppData) getApplicationContext();
        int serverResponseCode = 0;
        try {
            serverResponseCode = NetworkManager.userDelete("/api/user", tokenData.getToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // on success, move to login screen
        if (serverResponseCode == 204) {
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("User Delete")
                    .setMessage("User account was deleted successfully")
                    .setPositiveButton("Okay", (dialog, which) -> openLoginActivity())
                    .show();
        } else {
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("User Delete")
                    .setMessage("User account deletion failed")
                    .setPositiveButton("Okay", (dialog, which) -> openLoginActivity())
                    .show();
        }
    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }

    public static Boolean getTextToSpeechBool() {
        return textToSpeech;
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();

        // revoke authentication token
        AppData tokenData = (AppData) getApplicationContext();
        tokenData.clearToken();
    }
}