package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;
import com.example.logintest.data.NetworkManager;

import java.io.IOException;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Grab all the stuff on screen
        Button resetPasswordButton = findViewById(R.id.buttonResetPassword);
        Button returnLoginButton = findViewById(R.id.buttonReturnLoginPasswordReset);
        TextView textViewResetPassword = findViewById(R.id.textViewResetPassword);
        EditText editTextEmailPasswordReset = findViewById(R.id.editTextTextEmailAddressReset);

        // Set size of textViews
        textViewResetPassword.setTextSize(SettingsStyle.getFontSize());

        // Set size of editTexts
        editTextEmailPasswordReset.setTextSize(SettingsStyle.getFontSize());

        resetPasswordButton.setOnClickListener(v -> resetPassword(
                editTextEmailPasswordReset.getText().toString()
        ));
        returnLoginButton.setOnClickListener(v -> openLoginActivity());
    }

    private void resetPassword(String email) {
        if ((!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())){
            // check response for success
            int serverResponseCode = 0;
            try {
                serverResponseCode = NetworkManager.sendReset("/api/reset_password_request", email);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // on success, move to openDashboardActivity
            if (serverResponseCode == 200) {
                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Password Reset")
                        .setMessage("Your password was reset successfully")
                        .setPositiveButton("Okay", (dialog, which) -> this.finish())
                        .show();
            } else {
                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Password Reset Error")
                        .setMessage("Server unable to reset password")
                        .setPositiveButton("Okay", (dialog, which) -> this.finish())
                        .show();
            }

        }
    }

    private void openLoginActivity() {
        // TODO how to set up login with new info?
        // I don't know what this means @Paul
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }

}