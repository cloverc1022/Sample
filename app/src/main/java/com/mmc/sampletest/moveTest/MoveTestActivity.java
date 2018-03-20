package com.mmc.sampletest.moveTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.mmc.sampletest.R;

public class MoveTestActivity extends AppCompatActivity {

    private MoveTestViewGroup content;
    private MoveTestView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_test);

        content = (MoveTestViewGroup) findViewById(R.id.moveTestViewGroup);
//        textView = (MoveTestView) findViewById(R.id.textView);

    }
}
