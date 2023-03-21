package com.example.prm_final;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList <Film> films;
    TextView noResult;
    HorizontalScrollView hsc;
    TabLayout category;
    DatabaseFilm dbf;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbf = new DatabaseFilm(getContext(), null , null , 1);
        films = dbf.getAllListFilm();
        noResult = view.findViewById(R.id.textView2);
        noResult.setVisibility(view.GONE);
        recyclerView = view.findViewById(R.id.recyclerView);
        hsc = view.findViewById(R.id.horizontalScrollView);
        renderFilmList("");

        searchView = view.findViewById(R.id.searchView);
        category = view.findViewById(R.id.category);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                category.getTabAt(0).select();
                if (newText == "") {
                    films = dbf.getAllListFilm();
                    renderFilmList("");
                }
                else {
                    String name = "%" + newText + "%";
                    films = dbf.findFilmByName(name);
                    renderFilmList("");
                }
                    return true;
            }
        });
        category.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().toString().equals("All")) {
                    films = dbf.getAllListFilm();
                    renderFilmList("tabs");
                } else {
                    films = dbf.findFilmByCategory(tab.getText().toString());
                    renderFilmList("tabs");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void renderFilmList(String type) {
        if (type == "tabs") {
            if (films == null) {
                recyclerView.setVisibility(View.GONE);
                noResult.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                noResult.setVisibility(View.GONE);
                recyclerAdapterSearch adapter = new recyclerAdapterSearch(films, getContext());
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
            }
        } else {
            if (films == null) {
                recyclerView.setVisibility(View.GONE);
                hsc.setVisibility(View.GONE);
                noResult.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                hsc.setVisibility(View.VISIBLE);
                noResult.setVisibility(View.GONE);
                recyclerAdapterSearch adapter = new recyclerAdapterSearch(films, getContext());
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
            }
        }
    }
}