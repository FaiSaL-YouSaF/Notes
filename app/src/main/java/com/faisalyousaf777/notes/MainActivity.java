package com.faisalyousaf777.notes;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.faisalyousaf777.notes.fragment_categories.CategoriesFragment;
import com.faisalyousaf777.notes.fragment_favorites.FavoritesFragment;
import com.faisalyousaf777.notes.fragment_home.NotesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigationrail.NavigationRailView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        NavigationRailView navigationRail = findViewById(R.id.navigation_rail);

        Configuration config = getResources().getConfiguration();
        int screenWidth = config.screenWidthDp;
        if (screenWidth >= 600) {
            navigationRail.setVisibility(NavigationRailView.VISIBLE);
            bottomNavigation.setVisibility(BottomNavigationView.GONE);
        } else {
            navigationRail.setVisibility(NavigationRailView.GONE);
            bottomNavigation.setVisibility(BottomNavigationView.VISIBLE);
        }



        NavigationBarView.OnItemSelectedListener itemSelectedListener = item -> {
            if (item.getItemId() == R.id.nav_home) {
                loadContentFragment(NotesFragment.newInstance());
            } else if (item.getItemId() == R.id.nav_categories) {
                loadContentFragment(CategoriesFragment.newInstance());
            } else if (item.getItemId() == R.id.nav_favorites) {
                loadContentFragment(FavoritesFragment.newInstance());
            } else if (item.getItemId() == R.id.nav_settings) {
                Toast.makeText(this, "Just for testing", Toast.LENGTH_SHORT).show();
//                loadContentFragment(new SettingsFragment());
            } else {
                return false;
            }
            return true;
        };

        bottomNavigation.setOnItemSelectedListener(itemSelectedListener);
        bottomNavigation.setSelectedItemId(R.id.nav_home);

        navigationRail.setOnItemSelectedListener(itemSelectedListener);
        navigationRail.setSelectedItemId(R.id.nav_home);
    }

    private void loadContentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_content, fragment)
                .commit();
    }
}