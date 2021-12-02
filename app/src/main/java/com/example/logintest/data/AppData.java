package com.example.logintest.data;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.logintest.data.sensors.TestDataManager;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.HashMap;

public class AppData extends Application {
    private HashMap<Integer, TestDataManager> tdms = new HashMap<>();

    private String token;

    public void setToken(String tok) {
        token = tok;
    }

    public String getToken() {
        return token;
    }

    public void clearToken() {
        token = null;
    }

    public void updateTdm(int testnum, TestDataManager tdm) {
        tdms.put(testnum, tdm);
    }

    public void clearTdms() {
        tdms = new HashMap<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getBase64TestData() {
        // convert the tdms hashmap into a base64 string
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(tdms);
            oos.close();

            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch(Exception e) {
            return "null";
        }
    }
}
