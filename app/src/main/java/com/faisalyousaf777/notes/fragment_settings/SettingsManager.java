package com.faisalyousaf777.notes.fragment_settings;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class SettingsManager {
    private static SettingsManager instance;
    private static SharedPreferences sharedPreferences;

    private SettingsManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static synchronized SettingsManager getInstance(Context context) {
        if (instance == null) {
            instance = new SettingsManager(context.getApplicationContext());
        }
        return instance;
    }

    public String getThemePreference() {
        return sharedPreferences.getString("theme_preference", "system_default");
    }

    public String getFontStyle() {
        return sharedPreferences.getString("font_style_preference", "sans_serif");
    }

//    public int getTextSize() {
//        return sharedPreferences.getInt("text_size_preference", 16);
//    }
//
//    public String getDefaultCategory() {
//        return sharedPreferences.getString("default_category", "General");
//    }

    // Add more getters as needed
}
