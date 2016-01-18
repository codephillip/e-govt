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

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.codephillip.intmain.e_govt.R;
import com.codephillip.intmain.e_govt.chapter.ChapterActivity;
import com.codephillip.intmain.e_govt.provider.todayweather.TodayweatherColumns;


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
                .inflate(R.layout.district_record_row, parent, false);
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

        String textData = dataCursor.getString(dataCursor.getColumnIndex(TodayweatherColumns.NAME));
//        String imageUrl = dataCursor.getString(dataCursor.getColumnIndex(DistrictsColumns.IMAGE));

//        holder.time.setText(time);
//        holder.date.setText(date);
        holder.textView.setText(textData);

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
// generate random color
        int color1 = generator.getRandomColor();
// generate color based on a key (same key returns the same color), useful for list/grid views
//        int color2 = generator.getColor("user@gmail.com")

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .width(140)  // width in px
                .height(140) // height in px
                .endConfig()
                .buildRound(textData.substring(0,1), color1);
//                .buildRound("A", Color.RED);
//        TextDrawable drawable = TextDrawable.builder()
//                .buildRect("A", R.color.colorAccent);
//                .buildRound("A", Color.RED);
        holder.imageView.setImageDrawable(drawable);

//        Utility.picassoCircleLoader(context, holder.imageView, R.drawable.uganda_flag);
    }

    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 0 : dataCursor.getCount();
    }
}