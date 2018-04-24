package com.mmc.sampletest.customView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.mmc.sampletest.R;

public class TestActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private int x, y;
    private Path path;
    private boolean isDrawing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        paint.setStrokeWidth(30);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDrawing = true;
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }

    Thread drawThread = new Thread(){
        @Override
        public void run() {
            super.run();
            while (isDrawing){
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);

                x += 1;
                y = (int)(100*Math.sin(x*2*Math.PI/180) + 400);
                if (path == null){
                    path = new Path();
                }
                path.lineTo(x, y);
                canvas.drawPath(path, paint);

                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    };
}
