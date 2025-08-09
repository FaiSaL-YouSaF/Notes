package com.faisalyousaf777.notes.ui.main;

import static com.faisalyousaf777.notes.utils.NoteUtils.CATEGORY_ALL;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faisalyousaf777.notes.R;
import com.faisalyousaf777.notes.data.Note;
import com.faisalyousaf777.notes.viewmodel.NoteViewModel;

import java.time.LocalDateTime;
import java.util.List;


public class NotesFragment extends Fragment {
    private static final String CATEGORY_NAME = "category";
    private NoteViewModel noteViewModel;
    private NoteAdapter adapter;
    private String category;

    public NotesFragment() {
        // Required empty public constructor
    }

    public static NotesFragment newInstance(final String category) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_NAME, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(CATEGORY_NAME);
            noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        RecyclerView rvNotes = view.findViewById(R.id.rv_notes);
        rvNotes.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NoteAdapter(this.getActivity());
        if (category == null || category.isBlank() || category.equals(CATEGORY_ALL)) {


            noteViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes -> {
                if (notes != null && !notes.isEmpty()) {
                    // If there are notes, set the adapter with the notes
                    adapter.setNotes(notes);
                } else {
                    // If no notes, show a placeholder note
                    adapter.setNotes(List.of());
                    Log.d("ALL_NOTES", "onCreateView: List of All Notes is empty : " + category);
                }
            });
        } else {
            // Observe the notes based on the category
            noteViewModel.getNotesByCategory(category).observe(getViewLifecycleOwner(), notes -> {
                if (notes != null && !notes.isEmpty()) {
                    adapter.setNotes(notes);
                } else {
                    adapter.setNotes(List.of());
                    Log.d("CATEGORY_NOTES", "onCreateView: List of Category Notes is empty : " + category);
                }
            });
        }
        rvNotes.setAdapter(adapter);
        return view;
    }
}