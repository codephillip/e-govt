package com.codephillip.intmain.e_govt;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codephillip.intmain.e_govt.provider.chapters.ChaptersColumns;

public class ChapterDetailsActivity extends AppCompatActivity {
    private CollapsingToolbarLayout ctb;
    private int default_code = 0x000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String intentString = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        Log.d("INTENT", intentString);

        ImageView toolbarImage = (ImageView) findViewById(R.id.image_chapter_details);
        TextView chapterText = (TextView) findViewById(R.id.chapter_text);
        TextView bodyText = (TextView) findViewById(R.id.body_text);


        CursorLoader cursorLoader = new CursorLoader(this, ChaptersColumns.CONTENT_URI, null, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        String chapterTitle = null;

        if (cursor.moveToFirst()){
            do {
                chapterTitle = cursor.getString(cursor.getColumnIndex(ChaptersColumns.TITLE));
                if (chapterTitle.equals(intentString)){
                    String chapterTitleString = cursor.getString(cursor.getColumnIndex(ChaptersColumns.TITLE));
                    String bodyTextString = cursor.getString(cursor.getColumnIndex(ChaptersColumns.STORY));
                    String imageUrl = cursor.getString(cursor.getColumnIndex(ChaptersColumns.IMAGE));
                    Log.d("CONTENT_PROVIDER", "RESULTS: " + chapterTitleString + "#" + bodyTextString + "#" + chapterTitle + "#" + imageUrl);
                    chapterText.setText(chapterTitleString);
                    bodyText.setText(bodyTextString);
                    Utility.picassoLoader(this, toolbarImage, imageUrl);
                    break;
                }
            }while (cursor.moveToNext());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(getBaseContext(), FeedBackActivity.class));

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* Collapsing toolbar */
        ctb = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
//        ctb.setTitle(intentString);
        /* Decode bitmap from the image */
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pushup1);/* Generate palette from the image bitmap */
        Bitmap bitmap = ((BitmapDrawable) toolbarImage.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int vibrant = palette.getVibrantColor(default_code);
                int vibrantLight = palette.getLightVibrantColor(default_code);
                int vibrantDark = palette.getDarkVibrantColor(default_code);
                int muted = palette.getMutedColor(default_code);
                int mutedLight = palette.getLightMutedColor(default_code);
                int mutedDark = palette.getDarkMutedColor(default_code);

                final int[] colorArray = {
                        vibrant, vibrantLight, vibrantDark, muted, mutedLight, mutedDark
                };
                /* Set toolbar color from the palette */
                try {
                    ctb.setContentScrimColor(colorArray[Utility.randInt(0, 5)]);
                } catch (Exception e) {
                    e.printStackTrace();
                    ctb.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });
    }
}
