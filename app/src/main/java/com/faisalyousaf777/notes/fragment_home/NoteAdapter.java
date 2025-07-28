package com.faisalyousaf777.notes.fragment_home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faisalyousaf777.notes.commons.OnAdapterItemClickListener;
import com.faisalyousaf777.notes.R;
import com.faisalyousaf777.notes.commons.entity.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private final List<Note> listOfNotes;
    private final OnAdapterItemClickListener onAdapterItemClickListener;

    public NoteAdapter(List<Note> listOfNotes, OnAdapterItemClickListener onAdapterItemClickListener) {
        this.listOfNotes = listOfNotes;
        this.onAdapterItemClickListener = onAdapterItemClickListener;
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
        return listOfNotes.size();
    }

//    public void removeItem(int position) {
//        listOfNotes.remove(position);
//        notifyItemRemoved(position);
//    }

//    public void restoreItem(Note note, int position) {
//        listOfNotes.add(position, note);
//        notifyItemInserted(position);
//    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}