package com.faisalyousaf777.notes;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
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

public class AddNote extends AppCompatActivity {

    AppCompatEditText etTitle, etContent;
    CoordinatorLayout coordinatorLayoutTopAppBar;
    MaterialToolbar topAppBar;
    private NotesDAO notesDAO;
    boolean isSaved = false;
    boolean isFavorite = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTitle = findViewById(R.id.etTitleAddNote);
        etContent = findViewById(R.id.etContentAddNote);
        coordinatorLayoutTopAppBar = findViewById(R.id.toolbarLayout);
        topAppBar = findViewById(R.id.topAppBar);
        
        notesDAO = new NotesDAO(this);
        
        topAppBar.setNavigationOnClickListener(view -> saveNote());
        topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.btnDone) {
                saveNote();
                isSaved = true;
                finish();
            } else if (item.getItemId() == R.id.btnFavorite) {
                isFavorite = !isFavorite;
                item.setChecked(isFavorite);
                item.setIcon(isFavorite ? R.drawable.baseline_star_24 : R.drawable.outline_star_border_24);
            }
            return true;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!isSaved) {
            saveNote();
        }
    }

    public void saveNote() {
        String title = Objects.requireNonNull(etTitle.getText()).toString().trim();
        String content = Objects.requireNonNull(etContent.getText()).toString().trim();
        if (!title.isBlank() || !content.isBlank()) {
            notesDAO.insertNote(new Note.Builder()
                    .setTitle(title)
                    .setContent(content)
                    .setIsFavorite(isFavorite)
                    .setCreatedAt(LocalDateTime.now())
                    .build());
            setResult(RESULT_OK);
        }
    }
    
}