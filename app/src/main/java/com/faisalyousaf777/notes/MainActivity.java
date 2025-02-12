package com.faisalyousaf777.notes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnAdapterItemClickListener {

    public static final String NOTE_ID = "note_id";
    MaterialToolbar topAppBar;
    RecyclerView rvNotes;
    NoteAdapter noteAdapter;
    DbHelper db;
    private List<Note> fetchedNotes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        topAppBar = findViewById(R.id.topAppBar);
        rvNotes = findViewById(R.id.rvNotes);

        db = DbHelper.getInstance(this);
        fetchedNotes = db.getAllNotes();

        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        refreshAdapter();

        // Set the insets
        ViewCompat.setOnApplyWindowInsetsListener(rvNotes, (v, insets) -> {
            Insets insets1 = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(insets1.left, insets1.top, insets1.right, insets1.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        // Set the menu item click listeners
        topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.btn_add) {
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.btn_edit) {
                Toast.makeText(this, "Edit will be available soon", Toast.LENGTH_SHORT).show();
            } else if (item.getItemId() == R.id.btn_search) {
                Toast.makeText(this, "Search will be available soon", Toast.LENGTH_SHORT).show();
            }
            return false;
        });
    }

    ActivityResultLauncher<Intent> editNoteLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result != null && result.getResultCode() == RESULT_OK) {
            fetchedNotes = db.getAllNotes();
            refreshAdapter();
        }
    });

    @Override
    public void onItemClicked(View itemView, int position) {
        Intent intent = new Intent(MainActivity.this, EditNote.class);
        intent.putExtra(NOTE_ID, fetchedNotes.get(position).getId());
        editNoteLauncher.launch(intent);
    }

    @Override
    public void onItemLongClicked(View itemView, int position) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Delete Note")
                .setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Yes", ((dialog, which) -> {
                    db.deleteNoteById(fetchedNotes.get(position).getId());
                    fetchedNotes.remove(position);
                    noteAdapter.notifyItemRemoved(position);
                    dialog.dismiss();
                }))
                .setNegativeButton("No", ((dialog, which) -> dialog.dismiss()))
                .create();
        alertDialog.show();
    }

    public void refreshAdapter() {
        noteAdapter = new NoteAdapter(fetchedNotes, this);
        rvNotes.setAdapter(noteAdapter);
    }
}