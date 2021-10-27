package com.example.logintest.ui.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.logintest.R;

public class CreateUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        // Grab all the stuff on screen
        Button createAccount = findViewById(R.id.buttonCreateAccount);
        Button returnDashboardButton = findViewById(R.id.buttonReturnMainCreateAccount);
        TextView textViewCreateAccount = findViewById(R.id.textViewCreateAccount);
        EditText editTextFirstName = findViewById(R.id.editTextCreateFirstName);
        EditText editTextLastName = findViewById(R.id.editTextCreateLastName);
        EditText editTextCreateEmail = findViewById(R.id.editTextCreateEmail);
        EditText editTextCreatePassword = findViewById(R.id.editTextCreatePassword);
        EditText editTextCreateConfirmPassword = findViewById(R.id.editTextCreateConfirmPassword);

        // Set size of textViews
        textViewCreateAccount.setTextSize(SettingsStyle.getFontSize());

        // Set size of editTexts
        editTextFirstName.setTextSize(SettingsStyle.getFontSize());
        editTextLastName.setTextSize(SettingsStyle.getFontSize());
        editTextCreateEmail.setTextSize(SettingsStyle.getFontSize());
        editTextCreatePassword.setTextSize(SettingsStyle.getFontSize());
        editTextCreateConfirmPassword.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        createAccount.setTextSize(SettingsStyle.getFontSize());
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        createAccount.setOnClickListener(v -> createAccount());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());
    }



    private void createAccount() {
        // Create a popup that says account created

    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }
}