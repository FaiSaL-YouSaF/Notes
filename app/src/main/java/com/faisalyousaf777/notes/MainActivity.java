package com.faisalyousaf777.notes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;
    RecyclerView rvNotes;
    NoteAdapter noteAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        topAppBar = findViewById(R.id.topAppBar);
        rvNotes = findViewById(R.id.rvNotes);


        DbHelper dbHelper = DbHelper.getInstance(this);
        List<Note> listOfNotes = dbHelper.getAllNotes();

        noteAdapter = new NoteAdapter(listOfNotes);
        noteAdapter.setOnAdapterItemClickListener((View itemView, int position) -> {
                Note note = listOfNotes.get(position);
                Intent intent = new Intent(MainActivity.this, EditNote.class);
                intent.putExtra("title", note.getTitle());
                intent.putExtra("content", note.getContent());
                startActivity(intent);
        });
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setAdapter(noteAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(rvNotes, (v, insets) -> {
            Insets insets1 = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(insets1.left, insets1.top, insets1.right, insets1.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.btn_add) {
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.btn_edit) {
                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
            } else if (item.getItemId() == R.id.btn_search) {
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
            }
            return false;
        });
    }
}