package com.faisalyousaf777.notes;

import static com.faisalyousaf777.notes.MainActivity.NOTE_ID;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Objects;

public class EditNote extends AppCompatActivity {

    AppCompatEditText etTitle, etContent;
    CoordinatorLayout coordinatorLayoutTopAppBar;
    MaterialToolbar topAppBar;
    DbHelper db;
    boolean isUpdated;
    int noteId;

    @SuppressWarnings("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTitle = findViewById(R.id.etTitleEditNote);
        etContent = findViewById(R.id.etContentEditNote);
        coordinatorLayoutTopAppBar = findViewById(R.id.coordinatorLayoutTopAppBar);
        topAppBar = findViewById(R.id.topAppBar);

        isUpdated = false;
        db = DbHelper.getInstance(this);
        noteId = getIntent().getIntExtra(NOTE_ID, -1);
        if (noteId != -1) {
            Note note = db.getNoteById(noteId);
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
        }

        topAppBar.setNavigationOnClickListener(view -> {
            if (!isUpdated) {
                showDiscardDialog();
            } else {
                finish();
            }
        });
        topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.btn_done) {
                updateNote(noteId);
                finish();
            }
            return true;
        });
    }

    public void updateNote(int noteId) {
        String title = Objects.requireNonNull(etTitle.getText()).toString().trim();
        String content = Objects.requireNonNull(etContent.getText()).toString().trim();
        if (!title.isBlank() || !content.isBlank()) {
            db.updateNoteById(noteId, new Note(title, content));
            setResult(RESULT_OK);
            isUpdated = true;
        }
    }

    private void showDiscardDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Discard Changes?")
                .setMessage("Are you sure you want to discard the changes?")
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .create();
        alertDialog.show();
    }
}