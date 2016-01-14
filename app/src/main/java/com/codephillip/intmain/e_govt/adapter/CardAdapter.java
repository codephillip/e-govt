package com.codephillip.intmain.e_govt.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codephillip.intmain.e_govt.R;

/**
 * Created by codephillip on 12/26/15.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private static Context context;
    View cardview;
    Cursor dataCursor;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView topic;
        public ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            view = v;
            topic = (TextView) v.findViewById(R.id.topic);
            imageView = (ImageView) v.findViewById(R.id.image);

            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d("RECYCLER", "CLICK");
//                    context.startActivity(new Intent(context, StretchDetailActivity.class).putExtra(Intent.EXTRA_TEXT, topic.getText()));
                }
            });
        }
    }
    public CardAdapter(Context context, Cursor cursor) {
        this.context = context;
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
//        dataCursor.moveToPosition(position);
//
//        String workout = dataCursor.getString(dataCursor.getColumnIndex(FitContract.StretchData.WORKOUT));
//        String muscles = dataCursor.getString(dataCursor.getColumnIndex(FitContract.StretchData.MUSCLES));
//        String equipment = dataCursor.getString(dataCursor.getColumnIndex(FitContract.StretchData.EQUIPMENT));
//        String image1 = dataCursor.getString(dataCursor.getColumnIndex(FitContract.StretchData.IMAGE1));
//        Log.d("STRETCH_ADAPTER", workout + "#" + muscles + "#" + equipment + "#" + image1);
//
//        holder.topic.setText(workout);
//        holder.muscle.setText(muscles);
//        holder.equip.setText(equipment);
//        Utility.picassoLoader(context, holder.imageView, image1);
    }
    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 10 : dataCursor.getCount();
    }
}