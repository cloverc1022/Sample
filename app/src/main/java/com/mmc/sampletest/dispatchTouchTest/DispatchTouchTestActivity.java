package com.mmc.sampletest.dispatchTouchTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.mmc.sampletest.R;

public class DispatchTouchTestActivity extends AppCompatActivity {
    public static final String TAG = "ChildView";
    private ParentView parent;
    private ChildView touch;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_touch_test);

        parent = (ParentView) findViewById(R.id.parent);
        touch = (ChildView) findViewById(R.id.touch);
        result = (TextView) findViewById(R.id.result);

//        touch.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.d(TAG, "down-------onTouch");
//                return true;
//            }
//        });
//        touch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "down-------onClick");
//            }
//        });
    }
}
