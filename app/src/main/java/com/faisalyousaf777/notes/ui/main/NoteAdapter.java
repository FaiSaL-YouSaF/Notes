package com.faisalyousaf777.notes.ui.main;

import static com.faisalyousaf777.notes.utils.NoteUtils.NOTE_ID;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.faisalyousaf777.notes.R;
import com.faisalyousaf777.notes.data.Note;
import com.faisalyousaf777.notes.ui.edit.AddEditNoteActivity;
import com.faisalyousaf777.notes.viewmodel.NoteViewModel;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> listOfNotes = new ArrayList<>();
    private final NoteViewModel noteViewModel;

    public NoteAdapter(FragmentActivity fragmentActivity) {
        noteViewModel = new ViewModelProvider(fragmentActivity).get(NoteViewModel.class);
    }

    public void setNotes(List<Note> notes) {
        // Create a new list to avoid potential mutation issues
        listOfNotes = new ArrayList<>(notes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_view_holder, parent, false);
        return new NoteViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = listOfNotes.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvContent.setText(note.getContent());
        holder.tvUpdatedAt.setText(note.getCreatedAt().format(DateTimeFormatter.ofPattern("yy/MM/dd\nhh:mm a")).toUpperCase());

        // Adapter item click listener
        holder.itemView.setOnClickListener(view -> {
            // Open AddEditNote activity with note details
            Intent intent = new Intent(view.getContext(), AddEditNoteActivity.class);
            intent.putExtra(NOTE_ID, note.getNoteId());
            view.getContext().startActivity(intent);
        });
        // Adapter item long click listener
        holder.itemView.setOnLongClickListener(view -> {
            // Show delete note dialog
            AlertDialog deleteDialog = new AlertDialog.Builder(view.getContext())
                    .setTitle("Delete Note")
                    .setIcon(R.drawable.baseline_delete_outline_24)
                    .setMessage("Are you sure you want to delete this note?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        noteViewModel.delete(note);
                        notifyItemRemoved(position);
                        Toast.makeText(view.getContext(), "Note Deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .create();
            deleteDialog.show();
            Toast.makeText(view.getContext(), "Long Clicked : " + position, Toast.LENGTH_SHORT).show();
            return true;
        });

    }

    @Override
    public int getItemCount() {
        return listOfNotes.size();
    }


    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent, tvUpdatedAt;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvUpdatedAt = itemView.findViewById(R.id.tv_updated_at);
        }
    }
}