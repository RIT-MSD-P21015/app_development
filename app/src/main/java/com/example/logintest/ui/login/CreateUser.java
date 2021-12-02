package com.example.logintest.ui.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        Button createAccountButton = findViewById(R.id.buttonCreateAccount);
        Button returnLoginButton = findViewById(R.id.buttonReturnMainCreateAccount);
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
//        createAccountButton.setTextSize(SettingsStyle.getFontSize());
//        returnLoginButton.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        // TODO leave disabled until all data good
//        createAccountButton.setEnabled(true);
        createAccountButton.setOnClickListener(v -> createAccount(
                editTextFirstName.getText().toString(),
                editTextLastName.getText().toString(),
                editTextCreateEmail.getText().toString(),
                editTextCreatePassword.getText().toString(),
                editTextCreateConfirmPassword.getText().toString()
        ));
        returnLoginButton.setOnClickListener(v -> openLoginActivity());
    }

    private void createAccount(String firstName, String lastName, String email, String password, String confirmPassword) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        Log.d("FIRSTNAME:",firstName);
        Log.d("LASTNAME:",lastName);
        if (!password.equals(confirmPassword) || password.isEmpty() || password.length()<=5){
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Confirm password does not match password, or empty password");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        }
        else if (firstName.isEmpty() || lastName.isEmpty()){
            alertDialog.setTitle("Error");
            alertDialog.setMessage("First name or last name is not provided");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        }
        else if (!(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())){
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Email is not provided or not valid");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        }
        else {
            // TODO Add something to catch if no server response

            String body = "{ \"firstname\" : \"" + firstName + "\", \"lastname\" : \"" + lastName + "\", " +
                    "\"email\" : \"" + email + "\", \"password\" : \"" + password + "\" }";

            // check response for existing email or success
            int serverResponseCode = 0;
            try {
                serverResponseCode = NetworkManager.sendPost("/api/user", body);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // on success, move to openDashboardActivity
            if (serverResponseCode == 201) {
                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Account Created")
                        .setMessage("Account Created Successfully")
                        .setPositiveButton("Okay", (dialog, which) -> openLoginActivity())
                        .show();

            } else {
                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Account Error")
                        .setMessage("Please try again with a different email")
                        .setPositiveButton("Okay", null)
                        .show();
            }
        }

        // TODO test popups when server is up and running
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }
}