package com.example.logintest.data;

import android.app.Application;
import com.example.logintest.data.sensors.TestDataManager;
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

    public String getBase64TestData() {
        // TODO convert the tdms hashmap into a base64 string
        return null;
    }
}
