package com.codephillip.intmain.e_govt.chapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codephillip.intmain.e_govt.R;
import com.codephillip.intmain.e_govt.adapter.CardAdapter;
import com.codephillip.intmain.e_govt.provider.chapters.ChaptersColumns;

/**
 * A placeholder fragment containing a simple view.
 */
public class ChapterActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private final int LOADER_ID = 2;
    String intentString = "Ministry for Health";
    int loaderPosition = 1;

    public ChapterActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chapter, container, false);

        try {
            intentString = getActivity().getIntent().getStringExtra(Intent.EXTRA_TEXT);
            Log.d("INTENT", intentString);
        } catch (Exception e){
            e.printStackTrace();
        }

        adapter = new CardAdapter(getContext(), null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_stretch);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            String key = "FragNo";
            int fragPref = prefs.getInt(key, loaderPosition);
            if (fragPref == 1) loaderPosition = fragPref;
            else loaderPosition = fragPref;
            Log.d("PREF#", String.valueOf(loaderPosition));
            getLoaderManager().initLoader(LOADER_ID, null, this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("LOADER", "on_create_loader");
        if (loaderPosition == 1){
            Log.d("LOADER", "1");

            return new CursorLoader(getContext(), ChaptersColumns.CONTENT_URI, null,
                    ChaptersColumns.MINISTRY + " LIKE ?",
                    new String[] {intentString.concat("%")}, null);
        }
        else if (loaderPosition == 2){
            Log.d("LOADER", "2");

            return new CursorLoader(getContext(), ChaptersColumns.CONTENT_URI, null,
                    ChaptersColumns.DISTRICT + " LIKE ?",
                    new String[] {intentString.concat("%")}, null);
        }
        else {
            Log.d("LOADER", "else");

            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
