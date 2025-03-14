package com.faisalyousaf777.notes.fragment.content;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.faisalyousaf777.notes.OnAdapterItemClickListener;
import com.faisalyousaf777.notes.dao.CategoryDAO;
import com.faisalyousaf777.notes.entity.Category;
import com.faisalyousaf777.notes.R;
import com.faisalyousaf777.notes.adapter.CategoriesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class CategoriesFragment extends Fragment implements OnAdapterItemClickListener {

    private RecyclerView categoriesRecyclerView;
    private CategoriesAdapter categoriesAdapter;
    private CategoryDAO categoryDAO;
    private List<Category> fetchedNotes;
    private FloatingActionButton addCategoryFAB;


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
            refreshNotes();
        }
        return view;
    }

    private void refreshNotes() {
        categoryDAO = new CategoryDAO(getContext());
        fetchedNotes = categoryDAO.getAllCategories();
        categoriesAdapter = new CategoriesAdapter(fetchedNotes, this);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

    }

    @Override
    public void onItemClicked(View itemView, int position) {
        Toast.makeText(itemView.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClicked(View itemView, int position) {
        Toast.makeText(itemView.getContext(), "Item Long clicked", Toast.LENGTH_SHORT).show();
    }
}