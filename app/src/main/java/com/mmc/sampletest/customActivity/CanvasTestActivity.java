package com.mmc.sampletest.customActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.mmc.sampletest.R;

public class CanvasTestActivity extends AppCompatActivity {

    private ImageView canvas_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_test);

        canvas_one = (ImageView) findViewById(R.id.canvas_one);

        Bitmap bitmap = Bitmap.createBitmap(5, 5, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.RED);
        canvas_one.setImageBitmap(bitmap);
    }
}
