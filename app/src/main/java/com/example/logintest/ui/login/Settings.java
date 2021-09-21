package com.example.logintest.ui.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.logintest.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button aboutButton = findViewById(R.id.button_about);
        Button styleButton = findViewById(R.id.button_style);
        Button clearDataButton = findViewById(R.id.button_clear_data);
        Button returnMainButton = findViewById(R.id.button_return_main);

        aboutButton.setOnClickListener(v -> openAboutActivity());
        styleButton.setOnClickListener(v -> openStyleActivity());
        clearDataButton.setOnClickListener(v -> confirmClear());
        returnMainButton.setOnClickListener(v -> openDashboardActivity());
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
        new AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Clear Patient Info")
                .setMessage("Are you sure you want to clear all patient info?")
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", null)
                .show();

        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();
    }



    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }
}