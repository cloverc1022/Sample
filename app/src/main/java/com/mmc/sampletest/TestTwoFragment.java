package com.mmc.sampletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mmc.sampletest.customView.BezierActivity;
import com.mmc.sampletest.customView.BezierTestActivity;
import com.mmc.sampletest.customView.CanvasTestActivity;
import com.mmc.sampletest.customView.CircleActivity;
import com.mmc.sampletest.customView.DrawBitmapMeshActivity;
import com.mmc.sampletest.customView.EraserActivity;
import com.mmc.sampletest.customView.TestActivity;
import com.mmc.sampletest.customView.view.BrightnessSetPop;

/**
 * Created by 上海滩小马哥 on 2017/10/9.
 */

public class TestTwoFragment extends Fragment implements View.OnClickListener {

    private Button eraser, circle, drawBitmapMesh, canvas, Bezier, Bezier_Test, clock;
    private BrightnessSetPop brightnessSetPop;
    private Button popButton;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_two, null);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        eraser = (Button) view.findViewById(R.id.eraser);
        circle = (Button) view.findViewById(R.id.circle);
        drawBitmapMesh = (Button) view.findViewById(R.id.drawBitmapMesh);
        canvas = (Button) view.findViewById(R.id.canvas);
        Bezier = (Button) view.findViewById(R.id.Bezier);
        Bezier_Test = (Button) view.findViewById(R.id.Bezier_Test);
        Bezier_Test = (Button) view.findViewById(R.id.Bezier_Test);
        clock = (Button) view.findViewById(R.id.clock);
        popButton = (Button) view.findViewById(R.id.button5);
        popButton.setOnClickListener(this);
        eraser.setOnClickListener(this);
        circle.setOnClickListener(this);
        drawBitmapMesh.setOnClickListener(this);
        canvas.setOnClickListener(this);
        Bezier.setOnClickListener(this);
        Bezier_Test.setOnClickListener(this);
        clock.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.eraser:
                intent.setClass(getActivity(), EraserActivity.class);
                break;
            case R.id.circle:
                intent.setClass(getActivity(), CircleActivity.class);
                break;
            case R.id.drawBitmapMesh:
                intent.setClass(getActivity(), DrawBitmapMeshActivity.class);
                break;
            case R.id.canvas:
                intent.setClass(getActivity(), CanvasTestActivity.class);
                break;
            case R.id.Bezier:
                intent.setClass(getActivity(), BezierActivity.class);
                break;
            case R.id.Bezier_Test:
                intent.setClass(getActivity(), BezierTestActivity.class);
                break;
            case R.id.clock:
                intent.setClass(getActivity(), TestActivity.class);
                break;
            case R.id.button5:
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
                return;
            default:
                break;
        }
        getActivity().startActivity(intent);
    }
}
