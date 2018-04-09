package com.example.maysara_.myapplication.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPref {
    private static final String NAME = "APP";
    private Context context;

    public SharedPref(Context context) {
        this.context = context;
    }

    public String readItem(String key) {
        SharedPreferences prefs = context.getSharedPreferences(NAME, MODE_PRIVATE);
        String restoredText = prefs.getString(key, null);
        if (restoredText != null) {
            return prefs.getString(key, null);
        }
        return null;
    }

    public void storeItem(String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
        editor.clear();
    }

}
