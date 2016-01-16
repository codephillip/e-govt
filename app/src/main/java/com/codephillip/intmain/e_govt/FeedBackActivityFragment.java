package com.codephillip.intmain.e_govt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedBackActivityFragment extends Fragment {

    String intentString;
    boolean intentReceived = false;

    public FeedBackActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_feed_back, container, false);

        try {
            intentString = getActivity().getIntent().getStringExtra(Intent.EXTRA_TEXT);
            intentReceived = true;
            Log.d("INTENT", intentString);
        } catch (Exception e){
            e.printStackTrace();
        }

//        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

        if (intentReceived){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            String chapterStrip = "Feedback";

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(chapterStrip, intentString);
            editor.commit();
        }
    }
}
