package com.faisalyousaf777.notes.fragment_categories;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faisalyousaf777.notes.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


public class CategoriesFragment extends Fragment {

    private List<String> categories;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    public static CategoriesFragment newInstance() {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categories = List.of("All", "Work", "Personal", "Shopping", "Health", "Travel", "Finance");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialize UI components
        MaterialToolbar toolbar = view.findViewById(R.id.toolbar_categories);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout_categories);
        ViewPager2 viewPager = view.findViewById(R.id.view_pager_categories);

        // Set up TabItems in the TabLayout
        for (int tabIndex = 0; tabIndex < categories.size(); tabIndex++) {
            tabLayout.addTab(tabLayout.newTab().setText(categories.get(tabIndex)));
        }


    }
}