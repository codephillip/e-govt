package com.codephillip.intmain.e_govt.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codephillip.intmain.e_govt.R;
import com.codephillip.intmain.e_govt.Utility;
import com.codephillip.intmain.e_govt.chapter.ChapterDetailsActivity;
import com.codephillip.intmain.e_govt.provider.events.EventsColumns;

/**
 * Created by codephillip on 12/26/15.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private static Context context;
    View cardview;
    Cursor dataCursor;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView title;
        public ImageView imageView;
        public TextView date;
        public ViewHolder(View v) {
            super(v);
            view = v;
            title = (TextView) v.findViewById(R.id.topic);
            imageView = (ImageView) v.findViewById(R.id.image);
            date = (TextView) v.findViewById(R.id.date);

            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d("RECYCLER", "CLICK");
                    context.startActivity(new Intent(context, ChapterDetailsActivity.class).putExtra("eventIntent", title.getText()));
                }
            });
        }
    }
    public EventAdapter(Context context, Cursor cursor) {
        this.context = context;
        dataCursor = cursor;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        cardview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_cardview, parent, false);
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

        String title = dataCursor.getString(dataCursor.getColumnIndex(EventsColumns.TITLE));
        String image = dataCursor.getString(dataCursor.getColumnIndex(EventsColumns.IMAGE));
        String date = dataCursor.getString(dataCursor.getColumnIndex(EventsColumns.DATE));
        Log.d("STRETCH_ADAPTER", title + "#" + image + "#" + date);

        holder.title.setText(title);
        holder.date.setText(title);
        Utility.picassoLoader(context, holder.imageView, image);
    }
    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 0 : dataCursor.getCount();
    }
}