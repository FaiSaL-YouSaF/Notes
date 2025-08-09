package com.faisalyousaf777.notes.ui.edit;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.faisalyousaf777.notes.R;
import com.google.android.material.appbar.MaterialToolbar;

public class EditNote extends AppCompatActivity {

    AppCompatEditText etTitle, etContent;
    CoordinatorLayout coordinatorLayoutTopAppBar;
    MaterialToolbar toolbar;
//    private NotesDAO notesDAO;
    boolean isUpdated;
    int noteId;
    boolean isFavorite;

    @SuppressWarnings("MissingInflatedId")
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

        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        toolbar = findViewById(R.id.toolbar_add_edit_note);

//        isUpdated = false;
//        notesDAO = new NotesDAO(this);
//        noteId = getIntent().getIntExtra(NOTE_ID, -1);
//        if (noteId != -1) {
//            Note note = notesDAO.getNoteById(noteId);
//            etTitle.setText(note.getTitle());
//            etContent.setText(note.getContent());
//            isFavorite = note.isFavorite();
//        }

        toolbar.setNavigationOnClickListener(view -> {
            if (!isUpdated) {
                showDiscardDialog();
            } else {
                finish();
            }
        });
//        topAppBar.setOnMenuItemClickListener(item -> {
//            if (item.getItemId() == R.id.action_done) {
//                updateNote(noteId);
//                finish();
//            } else if (item.getItemId() == R.id.action_toggle_favorite) {
//                isFavorite = !isFavorite;
//                item.setChecked(isFavorite);
//                item.setIcon(isFavorite ? R.drawable.baseline_star_24 : R.drawable.outline_star_border_24);
//            }
//            return true;
//        });
    }

//    public void updateNote(int noteId) {
//        String title = Objects.requireNonNull(etTitle.getText()).toString().trim();
//        String content = Objects.requireNonNull(etContent.getText()).toString().trim();
//        if (!title.isBlank() || !content.isBlank()) {
//            notesDAO.updateNoteById(noteId, new Note.Builder()
//                    .setTitle(title)
//                    .setContent(content)
//                    .setIsFavorite(isFavorite)
//                    .setUpdatedAt(LocalDateTime.now())
//                    .build());
//            setResult(RESULT_OK);
//            isUpdated = true;
//        }
//    }

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