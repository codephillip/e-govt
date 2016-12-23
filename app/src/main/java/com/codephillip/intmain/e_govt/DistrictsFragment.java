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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codephillip.intmain.e_govt.adapter.DistrictAdapter;
import com.codephillip.intmain.e_govt.provider.districts.DistrictsColumns;
import com.codephillip.intmain.e_govt.sync.SyncAdapter;


public class DistrictsFragment extends Fragment  implements LoaderManager.LoaderCallbacks<Cursor> {
    private RecyclerView recyclerView;
    DistrictAdapter adapter;
    private int LOADER_ID = 1;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.districts_layout, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_record);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DistrictAdapter(getActivity(), null);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (Utility.dbCursor(DistrictsColumns.CONTENT_URI ,getContext()).getCount() <= 0)
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
        return new CursorLoader(getContext(), DistrictsColumns.CONTENT_URI, null, null, null,  DistrictsColumns.DISTRICT_NAME + " ASC");
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
        editor.putInt(FragString, 2);
        editor.apply();
    }
}
