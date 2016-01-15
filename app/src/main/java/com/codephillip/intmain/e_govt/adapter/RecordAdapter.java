package com.codephillip.intmain.e_govt.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codephillip.intmain.e_govt.chapter.ChapterActivity;
import com.codephillip.intmain.e_govt.R;
import com.codephillip.intmain.e_govt.Utility;
import com.codephillip.intmain.e_govt.provider.ministries.MinistriesColumns;


/**
 * Created by codephillip on 12/26/15.
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    Cursor dataCursor;
//    Context context;
    private static Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textview);
            imageView = (ImageView) v.findViewById(R.id.image);

            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d("RECYCLER", "CLICK");
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    String fragKey = "Frag";
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(fragKey, "Ministry");
                    editor.commit();
                    context.startActivity(new Intent(context, ChapterActivity.class).putExtra(Intent.EXTRA_TEXT, textView.getText()));
//                    context.startActivity(new Intent(context, ChapterActivity.class).putExtra(Intent.EXTRA_TEXT, workout.getText()));
                }
            });
        }
    }

    public RecordAdapter(Activity mContext, Cursor cursor) {
        dataCursor = cursor;
        context = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_row, parent, false);
        return new ViewHolder(cardview);
    }

    public Cursor swapCursor(Cursor cursor) {
        if (dataCursor == cursor) {
            return null;
        }
        Cursor oldCursor = dataCursor;
        this.dataCursor = cursor;
        if (cursor != null) {
            this.notifyDataSetChanged();
        }
        return oldCursor;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        dataCursor.moveToPosition(position);

        String textData = dataCursor.getString(dataCursor.getColumnIndex(MinistriesColumns.MINISTRY_NAME));
        String imageUrl = dataCursor.getString(dataCursor.getColumnIndex(MinistriesColumns.IMAGE));

//        holder.time.setText(time);
//        holder.date.setText(date);
        holder.textView.setText(textData);
        Utility.picassoCircleLoader(context, holder.imageView, imageUrl);
    }

    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 0 : dataCursor.getCount();
    }
}