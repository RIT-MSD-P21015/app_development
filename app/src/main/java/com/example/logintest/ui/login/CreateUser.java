package com.example.logintest.ui.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.logintest.R;
import com.example.logintest.data.NetworkManager;

import java.io.IOException;

public class CreateUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        // TODO don't run network on main
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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
//        createAccount.setTextSize(SettingsStyle.getFontSize());
//        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        // TODO leave disabled until all data good
        createAccount.setOnClickListener(v -> {
            createAccount(
                    editTextFirstName.getText().toString(),
                    editTextLastName.getText().toString(),
                    editTextCreateEmail.getText().toString(),
                    editTextCreatePassword.getText().toString()
            );
        });
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());
    }

    private void createAccount(String firstName, String lastName, String email, String passwd) {
        String body = "{ \"firstname\" : \"" + firstName + "\", \"lastname\" : \"" + lastName + "\", " +
                "\"email\" : \"" + email + "\", \"password\" : \"" + passwd + "\" }";

        // check response for existing email or success
        int code = 0;
        try {
            code = NetworkManager.sendPost("/api/user",body);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // on success, move to openDashboardActivity
        if(code == 201) {
            // TODO Create a popup that says account created
            openDashboardActivity();
        }

        // TODO on failure, pop up warning existing user
    }

    private void openDashboardActivity() {
        // TODO how to set up login with new info?
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }
}