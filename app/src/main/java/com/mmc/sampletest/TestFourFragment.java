package com.mmc.sampletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mmc.sampletest.RecycleViewTest.RecycleTestActivity;
import com.mmc.sampletest.RecycleViewTest.SectionActivity;
import com.mmc.sampletest.customview.BrightnessSetPop;
import com.mmc.sampletest.jsTest.JsTestActivity;
import com.mmc.sampletest.mapTest.MapTestActivity;
import com.mmc.sampletest.moveTest.MoveTestActivity;

/**
 * Created by 上海滩小马哥 on 2018/01/16.
 */

public class TestFourFragment extends Fragment {

    private BrightnessSetPop brightnessSetPop;
    private Button popButton;

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
        popButton = (Button) view.findViewById(R.id.button5);
        popButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (brightnessSetPop == null) {
                    brightnessSetPop = new BrightnessSetPop(getActivity(), new BrightnessSetPop.OnValueChangedListener() {
                        @Override
                        public void onValueChanged(int value) {

                        }

                        @Override
                        public void onDismiss() {
                        }
                    });
                }
                brightnessSetPop.showUpRise(popButton, 50);
            }
        });
        view.findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapTestActivity.class);
                startActivity(intent);
            }
        });
    }
}
