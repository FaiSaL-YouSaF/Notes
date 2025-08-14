package com.faisalyousaf777.notes.ui.edit;

import static com.faisalyousaf777.notes.utils.NoteUtils.NOTE_ID;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.faisalyousaf777.notes.R;
import com.faisalyousaf777.notes.data.Note;
import com.faisalyousaf777.notes.viewmodel.NoteViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDateTime;

public class AddEditNoteActivity extends AppCompatActivity {

    TextInputEditText etTitle, etContent;
    private NoteViewModel noteViewModel;
    private Note noteToEdit;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_edit_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_add_edit_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Initialize UI components
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        MaterialToolbar toolbar = findViewById(R.id.toolbar_add_edit_note);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        if (getIntent().getIntExtra(NOTE_ID, -1) != -1) {
            // If editing an existing note, set the title and content
            int noteId = getIntent().getIntExtra(NOTE_ID, -1);
            Log.d("NOTE_ID", "onCreate: " + noteId);
            noteViewModel.getById(noteId).observe(this, note -> {
                if (note != null) {
                    noteToEdit = note;
                    etTitle.setText(note.getTitle());
                    etContent.setText(note.getContent());
                    isFavorite = note.isFavorite();
                    toolbar.setTitle("Edit Note");
                } else {
                    Toast.makeText(this, "Note not found", Toast.LENGTH_SHORT).show();
                    Log.d("NOTE_TO_EDIT", "onCreate: Note is null");
                }
            });
        } else {
            // If creating a new note, set default values
            etTitle.setText("");
            etContent.setText("");
            isFavorite = false;
            toolbar.setTitle("Add Note");
        }
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteToEdit = noteViewModel.getById(getIntent().getIntExtra(NOTE_ID, -1)).getValue();


        toolbar.setNavigationOnClickListener(view -> showDiscardDialog());
        toolbar.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.action_toggle_favorite) {
                isFavorite = !isFavorite;
                menuItem.setChecked(isFavorite);
                menuItem.setIcon(isFavorite ? R.drawable.baseline_star_24 : R.drawable.outline_star_border_24);
            } else if (menuItem.getItemId() == R.id.action_done) {
                saveNote();
            } else if (menuItem.getItemId() == R.id.action_select_color) {
                Toast.makeText(this, "Select a Color", Toast.LENGTH_SHORT).show();
            } else {
                return false;
            }
            return true;
        });
    }

    private void saveNote() {
        String title = etTitle.getText() != null ? etTitle.getText().toString().trim() : "";
        String content = etContent.getText() != null ? etContent.getText().toString().trim() : "";
        if (title.isBlank() && content.isBlank()) {
            etTitle.setError("Title and Content cannot be empty");
            etContent.setError("Title and Content cannot be empty");
        } else {
            noteViewModel.insert(
                    new Note(
                            title,
                            content,
                            LocalDateTime.now(),
                            false,
                            false,
                            false,
                            false,
                            "Work",    // Get user's selected category from UI
                            "#FFFF00",    // Get user's selected color from UI
                            null,
                            null,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            null
                    )
            );
            finish(); // Close the activity after saving
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