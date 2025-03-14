package com.faisalyousaf777.notes.fragment.content;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faisalyousaf777.notes.AddNote;
import com.faisalyousaf777.notes.EditNote;
import com.faisalyousaf777.notes.entity.Note;
import com.faisalyousaf777.notes.adapter.NoteAdapter;
import com.faisalyousaf777.notes.OnAdapterItemClickListener;
import com.faisalyousaf777.notes.R;
import com.faisalyousaf777.notes.dao.NotesDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class NotesFragment extends Fragment implements OnAdapterItemClickListener {

    private FloatingActionButton addNoteFAB;
    public static final String NOTE_ID = "note_id";
    private RecyclerView notesRecyclerView;
    private NoteAdapter noteAdapter;
    private NotesDAO notesDAO;
    private List<Note> fetchedNotes;

    public NotesFragment() {
        // Required empty public constructor
    }

    public static NotesFragment newInstance() {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        if (getArguments() != null) {
            addNoteFAB = view.findViewById(R.id.addNoteFAB);
            addNoteFAB.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), AddNote.class);
                addNoteLauncher.launch(intent);
            });
            notesRecyclerView = view.findViewById(R.id.notesRecyclerView);
            notesDAO = new NotesDAO(getContext());
            notesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            refreshNotes();
        }
        return view;
    }

    @Override
    public void onItemClicked(View itemView, int position) {
        Intent intent = new Intent(itemView.getContext(), EditNote.class);
        intent.putExtra(NOTE_ID, fetchedNotes.get(position).getId());
        editNoteLauncher.launch(intent);
    }

    @Override
    public void onItemLongClicked(View itemView, int position) {
        AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext())
                .setTitle("Delete Note")
                .setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Yes", ((dialog, which) -> {
                    notesDAO.deleteNoteById(fetchedNotes.get(position).getId());
                    fetchedNotes.remove(position);
                    refreshNotes();
                    dialog.dismiss();
                }))
                .setNegativeButton("No", ((dialog, which) -> dialog.dismiss()))
                .create();
        alertDialog.show();
    }

    private final ActivityResultLauncher<Intent> addNoteLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    refreshNotes();
                }
            }
    );

    private final ActivityResultLauncher<Intent> editNoteLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    refreshNotes();
                }
            }
    );

    private void refreshNotes() {
        fetchedNotes = notesDAO.getAllNotes();
        noteAdapter = new NoteAdapter(fetchedNotes, this);
        notesRecyclerView.setAdapter(noteAdapter);
    }
}