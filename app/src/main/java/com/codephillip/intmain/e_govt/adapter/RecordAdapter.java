package com.codephillip.intmain.e_govt.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codephillip.intmain.e_govt.R;


/**
 * Created by codephillip on 12/26/15.
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    Cursor dataCursor;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textview);
            imageView = (ImageView) v.findViewById(R.id.image);
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
//        dataCursor.moveToPosition(position);
//
//        String time = dataCursor.getString(dataCursor.getColumnIndex(FitContract.RecordData.TIME));
//        String date = dataCursor.getString(dataCursor.getColumnIndex(FitContract.RecordData.DATE));
//        String distance = dataCursor.getString(dataCursor.getColumnIndex(FitContract.RecordData.DISTANCE));
//
//        holder.time.setText(time);
//        holder.date.setText(date);
//        holder.textView.setText(distance);
    }

    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 15 : dataCursor.getCount();
    }
}