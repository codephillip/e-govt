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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codephillip.intmain.e_govt.adapter.CardAdapter;
import com.codephillip.intmain.e_govt.provider.events.EventsColumns;

public class EventsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private final int LOADER_ID = 2;
    String intentString = "Jinja";
    boolean loaderBoolean = true;

    public EventsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chapter, container, false);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String fragKey = "Frag";
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(fragKey, "Event");
        editor.apply();

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

//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
//        String key = "FragNo";
//        int fragPref = prefs.getInt(key, 0);
//        if (fragPref == 4) {
//            loaderBoolean = true;
//        }
//        else {
//            loaderBoolean = false;
//        }
//        Log.d("PREF#", String.valueOf(loaderBoolean));

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("LOADER", "on_create_loader");
        if (loaderBoolean) return new CursorLoader(getContext(), EventsColumns.CONTENT_URI, null, null, null, null);
        else return null;
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
