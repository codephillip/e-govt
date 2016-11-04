package com.codephillip.intmain.e_govt;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codephillip.intmain.e_govt.adapter.RecordAdapter;
import com.codephillip.intmain.e_govt.provider.ministries.MinistriesColumns;
import com.codephillip.intmain.e_govt.sync.SyncAdapter;

public class MinistriesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final String TAG = MinistriesFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    RecordAdapter adapter;
    private int LOADER_ID = 4;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ministries_layout, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_record);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecordAdapter(getActivity(), null);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: "+ Utility.dbCursor(getContext()).getCount());
                if (Utility.dbCursor(getContext()).getCount() <= 0)
                    SyncAdapter.syncImmediately(getContext());
                else
                    swipeRefreshLayout.setRefreshing(false);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), MinistriesColumns.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }


    @Override
    public void onPause() {
        super.onPause();
        swipeRefreshLayout.setRefreshing(false);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String FragString = "FragNo";
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(FragString, 1);
        editor.apply();
    }
}
