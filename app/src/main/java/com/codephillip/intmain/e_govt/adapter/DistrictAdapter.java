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

import com.codephillip.intmain.e_govt.R;
import com.codephillip.intmain.e_govt.Utility;
import com.codephillip.intmain.e_govt.chapter.ChapterActivity;
import com.codephillip.intmain.e_govt.provider.districts.DistrictsColumns;


/**
 * Created by codephillip on 12/26/15.
 */
public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.ViewHolder> {
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
                    editor.putString(fragKey, "District");
                    editor.apply();
                    context.startActivity(new Intent(context, ChapterActivity.class).putExtra(Intent.EXTRA_TEXT, textView.getText()));
//                    context.startActivity(new Intent(context, ChapterActivity.class).putExtra(Intent.EXTRA_TEXT, workout.getText()));
                }
            });
        }
    }

    public DistrictAdapter(Activity mContext, Cursor cursor) {
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

        String textData = dataCursor.getString(dataCursor.getColumnIndex(DistrictsColumns.DISTRICT_NAME));
        String imageUrl = dataCursor.getString(dataCursor.getColumnIndex(DistrictsColumns.IMAGE));

//        holder.time.setText(time);
//        holder.date.setText(date);
        holder.textView.setText(textData);
        Utility.picassoCircleLoader(context, holder.imageView, R.drawable.uganda_flag);
    }

    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 0 : dataCursor.getCount();
    }
}