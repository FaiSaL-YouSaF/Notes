package com.faisalyousaf777.notes.fragment.content;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faisalyousaf777.notes.EditNote;
import com.faisalyousaf777.notes.entity.Note;
import com.faisalyousaf777.notes.OnAdapterItemClickListener;
import com.faisalyousaf777.notes.R;
import com.faisalyousaf777.notes.adapter.FavoritesAdapter;
import com.faisalyousaf777.notes.dao.NotesDAO;

import java.util.List;

public class FavoritesFragment extends Fragment implements OnAdapterItemClickListener {

    public static final String NOTE_ID = "note_id";
    private RecyclerView favoritesRecyclerView;
    private FavoritesAdapter favoritesAdapter;
    private List<Note> favoriteNotes;
    private NotesDAO notesDAO;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        if (getArguments() != null) {
            favoritesRecyclerView = view.findViewById(R.id.favoritesRecyclerView);
            favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            notesDAO = new NotesDAO(getContext());
            refreshNotes();
        }
        return view;
    }

    @Override
    public void onItemClicked(View itemView, int position) {
        Intent intent = new Intent(itemView.getContext(), EditNote.class);
        intent.putExtra(NOTE_ID, favoriteNotes.get(position).getId());
        editNoteLauncher.launch(intent);
    }

    @Override
    public void onItemLongClicked(View itemView, int position) {
        AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext())
                .setTitle("Delete Note")
                .setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Yes", ((dialog, which) -> {
                    notesDAO.deleteNoteById(favoriteNotes.get(position).getId());
                    favoriteNotes.remove(position);
                    refreshNotes();
                    dialog.dismiss();
                }))
                .setNegativeButton("No", ((dialog, which) -> dialog.dismiss()))
                .create();
        alertDialog.show();
    }

    private final ActivityResultLauncher<Intent> editNoteLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    refreshNotes();
                }
            }
    );

    public void refreshNotes() {
        favoriteNotes = notesDAO.getFavoriteNotes();
        favoritesAdapter = new FavoritesAdapter(favoriteNotes, this);
        favoritesRecyclerView.setAdapter(favoritesAdapter);
    }
}