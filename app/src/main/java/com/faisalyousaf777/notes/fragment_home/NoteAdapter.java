package com.faisalyousaf777.notes.fragment_home;

import static com.faisalyousaf777.notes.commons.NoteUtils.NOTE_ID;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faisalyousaf777.notes.R;
import com.faisalyousaf777.notes.commons.EditNote;
import com.faisalyousaf777.notes.commons.entity.Note;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private final List<Note> listOfNotes;

    public NoteAdapter(List<Note> listOfNotes) {
        this.listOfNotes = listOfNotes;
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
            Intent intent = new Intent(view.getContext(), EditNote.class);
            intent.putExtra(NOTE_ID, note.getNoteId());
            view.getContext().startActivity(intent);
        });
        // Adapter item long click listener
        holder.itemView.setOnLongClickListener(view -> {
            // Show delete note dialog
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