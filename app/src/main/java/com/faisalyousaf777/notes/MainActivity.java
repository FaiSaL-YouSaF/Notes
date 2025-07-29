package com.faisalyousaf777.notes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.faisalyousaf777.notes.commons.entity.Note;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.HashMap;
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

        List<String> categories = List.of("All", "Work", "Personal", "Shopping", "Health", "Travel", "Finance");


        // Set up ViewPager2 with a FragmentStateAdapter
        RecyclerViewPagerAdapter adapter = new RecyclerViewPagerAdapter(
                this,
                categories,
                new HashMap<String, List<Note>>() {{
                    put("All", List.of(
                            new Note.Builder()
                                    .setTitle("Sample Note")
                                    .setContent("This is a sample note content.")
                                    .build(),
                            new Note.Builder().setTitle("Another Note")
                                    .setContent("This is another sample note content.")
                                    .build()
                    ));
                    put("Work", List.of(
                            new Note.Builder()
                                    .setTitle("Sample Note")
                                    .setContent("This is a sample note content.")
                                    .build(),
                            new Note.Builder().setTitle("Another Note")
                                    .setContent("This is another sample note content.")
                                    .build()
                    ));
                    put("Personal", List.of(
                            new Note.Builder()
                                    .setTitle("Sample Note")
                                    .setContent("This is a sample note content.")
                                    .build(),
                            new Note.Builder().setTitle("Another Note")
                                    .setContent("This is another sample note content.")
                                    .build()
                    ));
                    put("Shopping", List.of(
                            new Note.Builder()
                                    .setTitle("Sample Note")
                                    .setContent("This is a sample note content.")
                                    .build(),
                            new Note.Builder().setTitle("Another Note")
                                    .setContent("This is another sample note content.")
                                    .build()
                    ));
                    put("Health", List.of(
                            new Note.Builder()
                                    .setTitle("Sample Note")
                                    .setContent("This is a sample note content.")
                                    .build(),
                            new Note.Builder().setTitle("Another Note")
                                    .setContent("This is another sample note content.")
                                    .build()
                    ));
                    put("Travel", List.of(
                            new Note.Builder()
                                    .setTitle("Sample Note")
                                    .setContent("This is a sample note content.")
                                    .build(),
                            new Note.Builder().setTitle("Another Note")
                                    .setContent("This is another sample note content.")
                                    .build()
                    ));
                    put("Finance", List.of(
                            new Note.Builder()
                                    .setTitle("Sample Note")
                                    .setContent("This is a sample note content.")
                                    .build(),
                            new Note.Builder().setTitle("Another Note")
                                    .setContent("This is another sample note content.")
                                    .build()
                    ));
                }}
        );
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(categories.get(position));
        }).attach();

    }
}