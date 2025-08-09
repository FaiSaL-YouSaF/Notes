package com.faisalyousaf777.notes.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.faisalyousaf777.notes.data.Note;
import com.faisalyousaf777.notes.utils.NoteCategory;

import java.util.List;

public class NotesPagerAdapter extends FragmentStateAdapter {

    private final NoteCategory[] categories;

    public NotesPagerAdapter(@NonNull FragmentActivity fragmentActivity, NoteCategory[] categories) {
        super(fragmentActivity);
        this.categories = categories;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String category = categories[position].toString();
        return NotesFragment.newInstance(category);
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }
}
