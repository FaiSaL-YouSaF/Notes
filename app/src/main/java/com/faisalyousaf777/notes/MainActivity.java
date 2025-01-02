package com.faisalyousaf777.notes;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvNotes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        rvNotes = findViewById(R.id.rvNotes);

        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setAdapter(new NoteAdapter(sampleNotes()));



//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    private List<Note> sampleNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("Title One", "This is the content one"));
        notes.add(new Note("Title Two", "This is the content two"));
        notes.add(new Note("Title Three", "This is the content three"));
        notes.add(new Note("Title Four", "This is the content four"));
        notes.add(new Note("Title Five", "This is the content five"));
        notes.add(new Note("Title Six", "This is the content six"));
        notes.add(new Note("Title Seven", "This is the content seven"));
        notes.add(new Note("Title Eight", "This is the content eight"));
        notes.add(new Note("Title Nine", "This is the content nine"));
        notes.add(new Note("Title Ten", "This is the content ten"));
        notes.add(new Note("Title Eleven", "This is the content eleven"));
        notes.add(new Note("Title Twelve", "This is the content twelve"));
        notes.add(new Note("Title Thirteen", "This is the content thirteen"));
        notes.add(new Note("Title Fourteen", "This is the content fourteen"));
        notes.add(new Note("Title Fifteen", "This is the content fifteen"));
        notes.add(new Note("Title Sixteen", "This is the content sixteen"));
        notes.add(new Note("Title Seventeen", "This is the content seventeen"));
        notes.add(new Note("Title Eighteen", "This is the content eighteen"));
        notes.add(new Note("Title Nineteen", "This is the content nineteen"));
        notes.add(new Note("Title Twenty", "This is the content twenty"));
        notes.add(new Note("Title Twenty-One", "This is the content twenty-one"));
        notes.add(new Note("Title Twenty-Two", "This is the content twenty-two"));
        notes.add(new Note("Title Twenty-Three", "This is the content twenty-three"));
        notes.add(new Note("Title Twenty-Four", "This is the content twenty-four"));
        notes.add(new Note("Title Twenty-Five", "This is the content twenty-five"));
        notes.add(new Note("Title Twenty-Six", "This is the content twenty-six"));
        notes.add(new Note("Title Twenty-Seven", "This is the content twenty-seven"));
        notes.add(new Note("Title Twenty-Eight", "This is the content twenty-eight"));
        notes.add(new Note("Title Twenty-Nine", "This is the content twenty-nine"));
        notes.add(new Note("Title Thirty", "This is the content thirty"));
        return notes;
    }
}