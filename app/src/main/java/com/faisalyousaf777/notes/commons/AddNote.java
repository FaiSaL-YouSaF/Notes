package com.faisalyousaf777.notes.commons;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.faisalyousaf777.notes.R;
import com.faisalyousaf777.notes.commons.dao.CategoryDAO;
import com.faisalyousaf777.notes.commons.dao.NotesDAO;
import com.faisalyousaf777.notes.commons.entity.Category;
import com.faisalyousaf777.notes.commons.entity.Note;
import com.google.android.material.appbar.MaterialToolbar;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class AddNote extends AppCompatActivity {

    AppCompatEditText etTitle, etContent;
    CoordinatorLayout coordinatorLayoutTopAppBar;
    MaterialToolbar topAppBar;
    private NotesDAO notesDAO;
    private CategoryDAO categoryDAO;
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
            } else if (item.getItemId() == R.id.btnCategory) {
                showCategoryDialog();
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

    @SuppressLint("MissingInflatedId")
    private void showCategoryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_category, null);
//        builder.setView(dialogView)
//                .setTitle("Select Category")
//                .setPositiveButton("OK", (dialog, id) -> {
////                    Spinner spinner = dialogView.findViewById(R.id.categorySpinner);
//                    categoryDAO = new CategoryDAO(this);
//                    List<Category> categories = categoryDAO.getAllCategories();
//                    String[] categoryNames = new String[categories.size()];
//                    for (int i = 0; i < categories.size(); i++) {
//                        categoryNames[i] = categories.get(i).getName();
//                    }
////                    spinner.setAutofillHints(categoryNames);
////                    String selectedCategory = spinner.getSelectedItem().toString();
//                    // Handle the selected category
//                    // For example, you can save it to the note or perform any other action
//                })
//                .setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());
//        builder.create().show();
    }
    
}