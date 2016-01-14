package com.codephillip.intmain.e_govt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codephillip.intmain.e_govt.adapter.RecordAdapter;

public class MinistriesFragment extends Fragment {
    private RecyclerView recyclerView;
    RecordAdapter adapter;
    private int LOADER_ID = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ministries_layout, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_record);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RecordAdapter(getActivity(), null);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
