package com.mmc.sampletest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mmc.sampletest.popup.FavouritePopupWindow;
import com.mmc.sampletest.popup.TestPopup;

/**
 * Created by 上海滩小马哥 on 2017/10/9.
 */

public class TestThreeFragment extends Fragment implements View.OnClickListener {

    private Button testPop, commentPop;
    private TestPopup testPopup;
    private FavouritePopupWindow favouritePopupWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_three, null);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        testPop = (Button) view.findViewById(R.id.test_popup);
        commentPop = (Button) view.findViewById(R.id.comment_popup);

        testPop.setOnClickListener(this);
        commentPop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_popup:
                if (null == testPopup) {
                    testPopup = new TestPopup(getActivity());
                }
                testPopup.showPopupWindow(testPop);
                break;
            case R.id.comment_popup:
                if (null == favouritePopupWindow) {
                    favouritePopupWindow = new FavouritePopupWindow(getActivity());
                }
                favouritePopupWindow.showPopupWindow(commentPop);
                break;
        }
    }
}
