package com.faisalyousaf777.notes;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.faisalyousaf777.notes.fragment_categories.CategoriesFragment;
import com.faisalyousaf777.notes.fragment_favorites.FavoritesFragment;
import com.faisalyousaf777.notes.fragment_home.NotesFragment;
import com.faisalyousaf777.notes.fragment_settings.SettingsFragment;
import com.faisalyousaf777.notes.fragment_home.NotesToolbarFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        loadToolbarFragment(NotesToolbarFragment.newInstance());
        loadContentFragment(NotesFragment.newInstance());

        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.homeNavigation) {
                loadToolbarFragment(NotesToolbarFragment.newInstance());
                loadContentFragment(NotesFragment.newInstance());
            } else if (item.getItemId() == R.id.categoriesNavigation) {
                loadToolbarFragment(NotesToolbarFragment.newInstance());
                loadContentFragment(CategoriesFragment.newInstance());
            } else if (item.getItemId() == R.id.favoritesNavigation) {
                loadToolbarFragment(NotesToolbarFragment.newInstance());
                loadContentFragment(FavoritesFragment.newInstance());
            } else if (item.getItemId() == R.id.settingsNavigation) {
                loadToolbarFragment(NotesToolbarFragment.newInstance());
                loadContentFragment(new SettingsFragment());
            } else {
                return false;
            }
            return true;
        });
    }

    private void loadToolbarFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.toolbarContainer, fragment)
                .commit();
    }

    private void loadContentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, fragment)
                .commit();
    }
}