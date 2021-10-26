package com.example.logintest.data;

import com.example.logintest.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {

            // TODO POST email and password to /api/token

            // TODO check if token received
            String token = null;

            if (token.equals(null)) {
                // TODO get first and last name from /api/user
                String firstName = "John";
                String lastName = "Doe";

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

    public Result<LoggedInUser> createUser(String firstName, String lastName, String username, String password) {
        try {

            // TODO POST info to /api/user

            // TODO check if success code returned
            int resultCode = 0;
            int good = 0;

            if (resultCode == good) {
                return new Result.Success<>(true);
            }
            else{
                return new Result.Failure<>("Failed to create user"); // TODO send server error?
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error creating user", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}