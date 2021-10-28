package com.example.logintest.data;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendData extends AsyncTask<String, Integer, Integer> {

    protected Integer doInBackground(String... params) {
        String field = params[0];
        String value = params[1];
        String token = params[2];

        String payload = "{ \"" + field + "\" : \"" + value + "\" }";
        String authHeaderValue = "Bearer " + token;

        int returnCode = 0;

        try {
            URL url = new URL(NetworkManager.getBaseURL().concat("/api/user"));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Authorization", authHeaderValue);
            con.setDoOutput(true);
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            returnCode = con.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnCode;
    }

    protected void onProgressUpdate(Integer... progress) {
        // pass
    }

    protected void onPostExecute(Integer result) {
        Log.d("TESTS", "Updated user information with response code " + result.toString());
    }
}
