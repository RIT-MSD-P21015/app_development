package com.example.logintest.data;

import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class NetworkManager {

    private final static String BaseURL = "http://fallriskdb-vm.main.ad.rit.edu:5000";

    // returns page data
    public static String sendGET(String location, String token) throws IOException {
        URL url = new URL(BaseURL.concat(location));
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        if(!token.isEmpty()) {
            String authHeaderValue = "Bearer " + token;
            urlConnection.setRequestProperty("Authorization", authHeaderValue);
        }

        String json = "";
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            json = readStream(in);
            Log.d("STATE",json);
        } finally {
            urlConnection.disconnect();
        }
        if(urlConnection.getResponseCode() == 200) {
            return json;
        }
        else {
            return "Bad response";
        }
    }
    private static String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    public static int sendPost(String location, String body) throws IOException {
        URL url = new URL(BaseURL.concat(location));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("sendPost",response.toString());

        }
        // TODO better error handling?
        return con.getResponseCode();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getToken(String username, String password) throws IOException {

        String auth = username + ":" + password;
        byte[] authBytes = auth.getBytes(StandardCharsets.UTF_8);
        String encodedAuth = "bad";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encodedAuth = Base64.getEncoder().encodeToString(authBytes);
        }
        String authHeaderValue = "Basic " + encodedAuth;

        URL url = new URL(BaseURL.concat("/api/tokens"));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization", authHeaderValue);
        con.setDoOutput(true);

        String token = "";
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("sendPost", response.toString());
            JSONObject json = new JSONObject(response.toString());

            token = json.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return token;
    }
}
