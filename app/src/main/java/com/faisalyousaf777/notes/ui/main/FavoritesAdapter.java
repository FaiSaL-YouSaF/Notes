package com.faisalyousaf777.notes.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faisalyousaf777.notes.data.Note;
import com.faisalyousaf777.notes.R;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private final List<Note> favoriteNotes;

    public FavoritesAdapter(List<Note> favoriteNotes) {
        this.favoriteNotes = favoriteNotes;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_view_holder, parent, false);
        return new FavoritesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        Note note = favoriteNotes.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvContent.setText(note.getContent());

        // Adapter item click listener


        // Adapter item long click listener

    }

    @Override
    public int getItemCount() {
        return favoriteNotes.size();
    }

    public static class FavoritesViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvContent;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
