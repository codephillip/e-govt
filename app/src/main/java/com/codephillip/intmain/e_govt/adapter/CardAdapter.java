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

import com.codephillip.intmain.e_govt.chapter.ChapterDetailsActivity;
import com.codephillip.intmain.e_govt.R;
import com.codephillip.intmain.e_govt.Utility;
import com.codephillip.intmain.e_govt.provider.chapters.ChaptersColumns;

/**
 * Created by codephillip on 12/26/15.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private static Context context;
    View cardview;
    Cursor dataCursor;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView title;
        public ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            view = v;
            title = (TextView) v.findViewById(R.id.topic);
            imageView = (ImageView) v.findViewById(R.id.image);

            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d("RECYCLER", "CLICK");
                    context.startActivity(new Intent(context, ChapterDetailsActivity.class).putExtra(Intent.EXTRA_TEXT, title.getText()));
                }
            });
        }
    }
    public CardAdapter(Context context, Cursor cursor) {
        CardAdapter.context = context;
        dataCursor = cursor;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        cardview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
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

        String title = dataCursor.getString(dataCursor.getColumnIndex(ChaptersColumns.TITLE));
        String image = dataCursor.getString(dataCursor.getColumnIndex(ChaptersColumns.IMAGE));
        Log.d("STRETCH_ADAPTER", title + "#" + image);

        holder.title.setText(title);
        Utility.picassoLoader(context, holder.imageView, image);
    }
    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 0 : dataCursor.getCount();
    }
}