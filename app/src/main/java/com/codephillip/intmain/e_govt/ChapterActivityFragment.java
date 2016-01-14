package com.codephillip.intmain.e_govt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codephillip.intmain.e_govt.adapter.CardAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class ChapterActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private final int LOADER_ID = 2;

    public ChapterActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chapter, container, false);
        adapter = new CardAdapter(getContext(), null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_stretch);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

//        return inflater.inflate(R.layout.fragment_chapter, container, false);
        return rootView;
    }
}
