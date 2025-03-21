package com.faisalyousaf777.notes.fragment.content;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.faisalyousaf777.notes.OnAdapterItemClickListener;
import com.faisalyousaf777.notes.adapter.NoteAdapter;
import com.faisalyousaf777.notes.dao.CategoryDAO;
import com.faisalyousaf777.notes.dao.NotesDAO;
import com.faisalyousaf777.notes.entity.Category;
import com.faisalyousaf777.notes.R;
import com.faisalyousaf777.notes.adapter.CategoriesAdapter;
import com.faisalyousaf777.notes.entity.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class CategoriesFragment extends Fragment implements OnAdapterItemClickListener {

    private RecyclerView categoriesRecyclerView;
    private CategoriesAdapter categoriesAdapter;
    private CategoryDAO categoryDAO;
    private List<Category> fetchedCategories;
    private FloatingActionButton addCategoryFAB;
    private NotesDAO notesDAO;
    private NoteAdapter notesAdapter;
    private List<Note> categoryNotes;


    public CategoriesFragment() {
        // Required empty public constructor
    }

    public static CategoriesFragment newInstance() {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        if (getArguments() != null) {
            categoriesRecyclerView = view.findViewById(R.id.categoriesRecyclerView);
            addCategoryFAB = view.findViewById(R.id.addCategoryFAB);
            addCategoryFAB.setOnClickListener(v -> {
                Toast.makeText(getContext(), "Add Category", Toast.LENGTH_SHORT).show();
            });
            categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            refreshCategories();
        }
        return view;
    }

    private void refreshCategories() {
        categoryDAO = new CategoryDAO(getContext());
        fetchedCategories = categoryDAO.getAllCategories();
        categoriesAdapter = new CategoriesAdapter(fetchedCategories, this);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

    }

    @Override
    public void onItemClicked(View itemView, int position) {
        notesDAO = new NotesDAO(getContext());
        categoryNotes = notesDAO.getNotesByCategory(fetchedCategories.get(position).getName());
        notesAdapter = new NoteAdapter(categoryNotes, this);
        categoriesRecyclerView.setAdapter(notesAdapter);
    }

    @Override
    public void onItemLongClicked(View itemView, int position) {
        Toast.makeText(itemView.getContext(), "Long clicked: " + fetchedCategories.get(position).getName(), Toast.LENGTH_SHORT).show();
        AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext())
                .setTitle("Delete Category")
                .setMessage("Are you sure you want to delete this category?")
                .setPositiveButton("Yes", ((dialog, which) -> {
                    categoryDAO.deleteCategory(fetchedCategories.get(position).getCategoryId());
                    fetchedCategories.remove(position);
                    refreshCategories();
                    dialog.dismiss();
                }))
                .setNegativeButton("No", ((dialog, which) -> dialog.dismiss()))
                .create();
        alertDialog.show();
    }
}