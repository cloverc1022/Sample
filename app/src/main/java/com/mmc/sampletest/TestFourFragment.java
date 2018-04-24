package com.mmc.sampletest;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.mmc.sampletest.Fingerprint.FingerprintTestActivity;
import com.mmc.sampletest.RecycleViewTest.RecycleTestActivity;
import com.mmc.sampletest.RecycleViewTest.SectionActivity;
import com.mmc.sampletest.SlidingConflictTest.SlidingConflictActivity;
import com.mmc.sampletest.customView.view.BrightnessSetPop;
import com.mmc.sampletest.dispatchTouchTest.DispatchTouchTestActivity;
import com.mmc.sampletest.jsTest.JsTestActivity;
import com.mmc.sampletest.mapTest.MapTestActivity;
import com.mmc.sampletest.moveTest.MoveTestActivity;
import com.mmc.sampletest.sqliteTest.SqliteTestActivity;

/**
 * Created by 上海滩小马哥 on 2018/01/16.
 */

public class TestFourFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_four, null);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecycleTestActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SectionActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MoveTestActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JsTestActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapTestActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DispatchTouchTestActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SlidingConflictActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SqliteTestActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(getActivity());
                imageView.setImageResource(R.mipmap.dialog_5);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.TYPE_SYSTEM_ERROR, 0, PixelFormat.TRANSPARENT);
                params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
                params.gravity = Gravity.TOP|Gravity.START;
                getActivity().getWindowManager().addView(imageView, params);
            }
        });
        view.findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FingerprintTestActivity.class);
                startActivity(intent);
            }
        });
    }
}
