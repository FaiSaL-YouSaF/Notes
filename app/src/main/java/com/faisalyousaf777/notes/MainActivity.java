package com.faisalyousaf777.notes;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.faisalyousaf777.notes.fragment.content.CategoriesFragment;
import com.faisalyousaf777.notes.fragment.content.FavoritesFragment;
import com.faisalyousaf777.notes.fragment.content.NotesFragment;
import com.faisalyousaf777.notes.fragment.content.SettingsFragment;
import com.faisalyousaf777.notes.fragment.toolbar.NotesToolbarFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout toolbarLayout;
    private BottomNavigationView bottomNavigation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        toolbarLayout = findViewById(R.id.toolbarLayout);

        bottomNavigation = findViewById(R.id.bottomNavigation);

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
                loadContentFragment(SettingsFragment.newInstance());
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