package com.faisalyousaf777.notes.fragment.content;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;

import com.faisalyousaf777.notes.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        // Setup preferences change listeners
        setupPreferenceChangeListeners();
    }

    private void setupPreferenceChangeListeners() {
        // Theme preference change listeners
        findPreference("theme_preference").setOnPreferenceChangeListener((preference, newValue) -> {
            applyTheme((String) newValue);
            return true;
        });

        // Font Style preference change listeners
        findPreference("font_style_preference").setOnPreferenceChangeListener((preference, newValue) -> {
            updateFontStyle((String) newValue);
            return true;
        });
        // Add more listeners for other preferences as needed
    }

    private void applyTheme(String themeValue) {
        switch (themeValue) {
            case "system_default":
                // Apply system default theme
                break;
            case "light":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case "dark":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            default:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
        }

        // Restart the activity to apply the theme immediately
        requireActivity().recreate();
    }

    private void updateFontStyle(String fontStyle) {
        // Update font style based on the selected value
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("font_style", fontStyle);
            editor.apply();
        } else {
            Toast.makeText(this.getContext(), "Error", Toast.LENGTH_SHORT).show();
        }

        // Restart the activity to apply the font style immediately
        requireActivity().recreate();
    }


}