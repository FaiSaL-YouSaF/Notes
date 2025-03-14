package com.faisalyousaf777.notes;

import static com.faisalyousaf777.notes.fragment.content.NotesFragment.NOTE_ID;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.faisalyousaf777.notes.dao.NotesDAO;
import com.faisalyousaf777.notes.entity.Note;
import com.google.android.material.appbar.MaterialToolbar;

import java.time.LocalDateTime;
import java.util.Objects;

public class EditNote extends AppCompatActivity {

    AppCompatEditText etTitle, etContent;
    CoordinatorLayout coordinatorLayoutTopAppBar;
    MaterialToolbar topAppBar;
    private NotesDAO notesDAO;
    boolean isUpdated;
    int noteId;
    boolean isFavorite;

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
        coordinatorLayoutTopAppBar = findViewById(R.id.toolbarLayout);
        topAppBar = findViewById(R.id.topAppBar);

        isUpdated = false;
        notesDAO = new NotesDAO(this);
        noteId = getIntent().getIntExtra(NOTE_ID, -1);
        if (noteId != -1) {
            Note note = notesDAO.getNoteById(noteId);
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
            isFavorite = note.isFavorite();
        }

        topAppBar.setNavigationOnClickListener(view -> {
            if (!isUpdated) {
                showDiscardDialog();
            } else {
                finish();
            }
        });
        topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.btnDone) {
                updateNote(noteId);
                finish();
            } else if (item.getItemId() == R.id.btnFavorite) {
                isFavorite = !isFavorite;
                item.setChecked(isFavorite);
                item.setIcon(isFavorite ? R.drawable.baseline_star_24 : R.drawable.outline_star_border_24);
            }
            return true;
        });
    }

    public void updateNote(int noteId) {
        String title = Objects.requireNonNull(etTitle.getText()).toString().trim();
        String content = Objects.requireNonNull(etContent.getText()).toString().trim();
        if (!title.isBlank() || !content.isBlank()) {
            notesDAO.updateNoteById(noteId, new Note.Builder()
                    .setTitle(title)
                    .setContent(content)
                    .setIsFavorite(isFavorite)
                    .setUpdatedAt(LocalDateTime.now())
                    .build());
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