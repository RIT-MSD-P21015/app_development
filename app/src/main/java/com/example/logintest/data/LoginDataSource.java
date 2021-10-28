package com.example.logintest.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.logintest.data.model.LoggedInUser;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Result<LoggedInUser> login(String username, String password) {

        try {
            // POST email and password to /api/token
            String token = NetworkManager.getToken(username, password);

            // check if token received
            if (!token.isEmpty()) {
                // get first and last name from /api/user
                String json = NetworkManager.sendGET("/api/user", token);
                JSONObject jsonObject = new JSONObject(json);
                String firstName = jsonObject.getString("firstname");
                String lastName = jsonObject.getString("lastname");;

                String displayName = firstName + " " + lastName;
                LoggedInUser currentUser = new LoggedInUser(token, displayName);
                return new Result.Success<>(currentUser);
            }
            else{
                return new Result.Failure<>("Bad username/password");
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}