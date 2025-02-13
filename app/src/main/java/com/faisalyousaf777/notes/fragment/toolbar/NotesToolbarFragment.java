package com.faisalyousaf777.notes.fragment.toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.faisalyousaf777.notes.R;
import com.google.android.material.appbar.MaterialToolbar;


public class NotesToolbarFragment extends Fragment {

    private MaterialToolbar toolbar;

    public NotesToolbarFragment() {
        // Required empty public constructor
    }

    public static NotesToolbarFragment newInstance() {
        NotesToolbarFragment fragment = new NotesToolbarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes_toolbar, container, false);
        if (getArguments() != null) {
            toolbar = view.findViewById(R.id.toolbar);
            toolbar.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.btnEdit) {
                    Toast.makeText(getContext(), "Edit Notes", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.btnSearch) {
                    Toast.makeText(getContext(), "Search Notes", Toast.LENGTH_SHORT).show();
                } else {
                    return false;
                }
                return true;
            });
        }
        return view;
    }
}