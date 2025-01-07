package com.faisalyousaf777.notes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    List<Note> listOfNotes;
    private OnAdapterItemClickListener onAdapterItemClickListener;
    private OnAdapterItemLongClickListener onAdapterItemLongClickListener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener onAdapterItemClickListener) {
        this.onAdapterItemClickListener = onAdapterItemClickListener;
    }
    public void setOnAdapterItemLongClickListener(OnAdapterItemLongClickListener onAdapterItemLongClickListener) {
        this.onAdapterItemLongClickListener = onAdapterItemLongClickListener;
    }

    public NoteAdapter(List<Note> listOfNotes) {
        this.listOfNotes = listOfNotes;
    }

    public void setListOfNotes(List<Note> listOfNotes) {
        this.listOfNotes = listOfNotes;
//        notifyDataSetChanged();
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
    }

    @Override
    public int getItemCount() {
        return listOfNotes.size();
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(@NonNull View itemView) {
                    if (onAdapterItemClickListener != null) {
                        onAdapterItemClickListener.onItemClicked(itemView, getAdapterPosition());
                        Toast.makeText(itemView.getContext(), "Item clicked at position: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(@NonNull View itemView) {
                    if (onAdapterItemLongClickListener != null) {
                        onAdapterItemLongClickListener.onItemLongClicked(itemView, getAdapterPosition());
                    Log.d("NoteAdapter", "onLongClick: Deleting note at position: " + getAdapterPosition());
                        AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext())
                                .setTitle("Delete Note")
                                .setMessage("Are you sure you want to delete this note?")
                                .setPositiveButton("Yes", (dialog, which) -> {
                                    DbHelper.getInstance(itemView.getContext()).deleteNoteById(listOfNotes.get(getAdapterPosition()).getId());
                                    listOfNotes.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                    notifyItemRangeChanged(getAdapterPosition(), listOfNotes.size());
                                })
                                .setNegativeButton("No", (dialog, which) -> {
                                    dialog.dismiss();
                                })
                                .create();
                        alertDialog.show();
                    }
                    return true;
                }
            });
        }
    }

    interface OnAdapterItemClickListener {
        void onItemClicked(View itemView, int position);
    }

    interface OnAdapterItemLongClickListener {
        void onItemLongClicked(View itemView, int position);
    }

}
