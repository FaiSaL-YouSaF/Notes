package com.faisalyousaf777.notes;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faisalyousaf777.notes.commons.entity.Note;
import com.faisalyousaf777.notes.fragment_home.NoteAdapter;

import java.util.List;
import java.util.Map;

public class RecyclerViewPagerAdapter extends RecyclerView.Adapter<RecyclerViewPagerAdapter.PagerViewHolder> {

    private final Context context;
    private final List<String> filters;
    private final Map<String, List<Note>> filteredDataMap;

    public RecyclerViewPagerAdapter(Context context, List<String> filters, Map<String, List<Note>> filteredDataMap) {
        this.context = context;
        this.filters = filters;
        this.filteredDataMap = filteredDataMap;
    }

    @NonNull
    @Override
    public PagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView rvColors = new RecyclerView(context);
        rvColors.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        return new PagerViewHolder(rvColors);
    }

    @Override
    public void onBindViewHolder(@NonNull PagerViewHolder holder, int position) {
        String filterKey = filters.get(position);
        List<Note> notesForTab = filteredDataMap.get(filterKey);
        NoteAdapter adapter = new NoteAdapter(notesForTab);
        holder.rvColors.setLayoutManager(new LinearLayoutManager(context));
        holder.rvColors.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return filters.size();
    }

    public static class PagerViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvColors;

        public PagerViewHolder(@NonNull RecyclerView recyclerView) {
            super(recyclerView);
            rvColors = recyclerView;
        }
    }
}
