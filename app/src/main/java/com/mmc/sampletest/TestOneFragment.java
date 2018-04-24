package com.mmc.sampletest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mmc.sampletest.customView.view.OneFourTestView;

/**
 * Created by 上海滩小马哥 on 2017/10/9.
 */

public class TestOneFragment extends Fragment {
    private OneFourTestView oneFourTestView;
    private int isStart = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_one, null);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        sweepGradientView = (SweepGradientView) view.findViewById(R.id.test_view);
//        sweepGradientView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isStart == 1){
//                    sweepGradientView.setMode(0);
//                    isStart = 0;
//                }else if (isStart == 0){
//                    sweepGradientView.setMode(1);
//                    isStart = 1;
//                }
//            }
//        });
        oneFourTestView = (OneFourTestView) view.findViewById(R.id.dadsadsa);
        oneFourTestView.setChangeStateListener(new OneFourTestView.ChangeStateListener() {
            @Override
            public void onChangeState(OneFourTestView.AREA area) {
                Toast.makeText(getActivity(), area+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
