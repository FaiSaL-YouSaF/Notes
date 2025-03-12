package com.faisalyousaf777.notes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faisalyousaf777.notes.Note;
import com.faisalyousaf777.notes.OnAdapterItemClickListener;
import com.faisalyousaf777.notes.R;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private final List<Note> favoriteNotes;
    private final OnAdapterItemClickListener onAdapterItemClickListener;

    public FavoritesAdapter(List<Note> favoriteNotes, OnAdapterItemClickListener onAdapterItemClickListener) {
        this.favoriteNotes = favoriteNotes;
        this.onAdapterItemClickListener = onAdapterItemClickListener;
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
        holder.itemView.setOnClickListener(v -> {
            if (onAdapterItemClickListener != null) {
                onAdapterItemClickListener.onItemClicked(v, position);
            }
        });

        // Adapter item long click listener
        holder.itemView.setOnLongClickListener(v -> {
            if (onAdapterItemClickListener != null) {
                onAdapterItemClickListener.onItemLongClicked(v, position);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return favoriteNotes.size();
    }

    public static class FavoritesViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvContent;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }
}
