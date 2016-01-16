package com.codephillip.intmain.e_govt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedBackActivityFragment extends Fragment {

    String intentString;
    boolean intentReceived = false;
    private TextInputLayout textInputLayoutTopic;
    private TextInputLayout textInputLayoutMessage;

    public FeedBackActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_feed_back, container, false);

        try {
            intentString = getActivity().getIntent().getStringExtra(Intent.EXTRA_TEXT);
            intentReceived = true;
            Log.d("INTENT", intentString);
        } catch (Exception e){
            e.printStackTrace();
        }

        Button send = (Button) rootView.findViewById(R.id.send);
        Button reset = (Button) rootView.findViewById(R.id.reset);
        final EditText editTopic = (EditText) rootView.findViewById(R.id.editTopic);
        final EditText editMessage = (EditText) rootView.findViewById(R.id.editMessage);

        textInputLayoutTopic = (TextInputLayout) rootView.findViewById(R.id.editTopicLayout);
        textInputLayoutMessage = (TextInputLayout) rootView.findViewById(R.id.editMessageLayout);
        textInputLayoutTopic.setErrorEnabled(true);
        textInputLayoutMessage.setErrorEnabled(true);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Send: ", "Sending ..");

                if (editTopic.getText().length() <= 0 || editMessage.getText().length() <= 0){
                    editTextError();
                }
                else {
                    Toast.makeText(getContext(), editTopic.getText() + "#" + editMessage.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTopic.setText("");
                editMessage.setText("");
            }
        });

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

    private void editTextError(){
        Resources res = getResources();
        textInputLayoutTopic.setError(res.getString(R.string.topic_required));
        textInputLayoutMessage.setError(res.getString(R.string.message_required));
    }
}
