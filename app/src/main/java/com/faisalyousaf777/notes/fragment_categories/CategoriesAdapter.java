package com.faisalyousaf777.notes.fragment_categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.faisalyousaf777.notes.commons.entity.Category;
import com.faisalyousaf777.notes.commons.OnAdapterItemClickListener;
import com.faisalyousaf777.notes.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private final List<Category> categories;
    private final OnAdapterItemClickListener onAdapterItemClickListener;

    public CategoriesAdapter(List<Category> categories, OnAdapterItemClickListener onAdapterItemClickListener) {
        this.categories = categories;
        this.onAdapterItemClickListener = onAdapterItemClickListener;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view_holder, parent, false);
        return new CategoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.tvCategory.setText(category.getName());

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
        return categories.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tvCategory;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}
