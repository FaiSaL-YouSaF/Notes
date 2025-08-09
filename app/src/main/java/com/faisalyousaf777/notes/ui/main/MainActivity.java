package com.faisalyousaf777.notes.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.faisalyousaf777.notes.R;
import com.faisalyousaf777.notes.ui.edit.AddEditNoteActivity;
import com.faisalyousaf777.notes.utils.NoteCategory;
import com.faisalyousaf777.notes.viewmodel.NoteViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        MaterialToolbar toolbar = findViewById(R.id.toolbar_main);
        TabLayout tabLayout = findViewById(R.id.tab_layout_categories);
        ViewPager2 viewPager = findViewById(R.id.view_pager_notes);
        FloatingActionButton fabAddNote = findViewById(R.id.fab_add_note);

        Toolbar.OnMenuItemClickListener menuItemClickListener = item -> {
            if (item.getItemId() == R.id.action_edit) {
                Toast.makeText(this, "Action Edit", Toast.LENGTH_SHORT).show();
            } else if (item.getItemId() == R.id.action_search) {
                Toast.makeText(this, "Action Search", Toast.LENGTH_SHORT).show();
            } else {
                return false;
            }
            return true;
        };
        toolbar.setOnMenuItemClickListener(menuItemClickListener);
        NoteCategory[] categories = NoteCategory.values();

        NotesPagerAdapter adapter = new NotesPagerAdapter(this, categories);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(categories[position].toString());
        }).attach();

        fabAddNote.setOnClickListener(view -> {
            // Open AddEditNote activity
            Intent intent = new Intent(this, AddEditNoteActivity.class);
            startActivity(intent);
        });

    }
}