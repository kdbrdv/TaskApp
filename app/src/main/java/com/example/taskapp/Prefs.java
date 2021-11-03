package com.example.taskapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveBoardState() {
        preferences.edit().putBoolean("isBoolean", true).apply();
    }

    public boolean isBoardShown() {
        return preferences.getBoolean("isBoardShown", false);
    }

    public void savePhoto(Uri uri){
        preferences.edit().putString("user_photo",String.valueOf(uri)).apply();
    }
    public String getPhoto(){
        return preferences.getString("user_photo",null);
    }

    public void setUserName(String name) {
        preferences.edit().putString("user_name", name).apply();
    }

    public String getUserName() {
        return preferences.getString("user_name", null);
    }

    public void deletePhoto() {
        preferences.edit().remove("user_photo").apply();
    }
}
