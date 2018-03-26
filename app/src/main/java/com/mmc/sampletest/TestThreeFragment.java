package com.mmc.sampletest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mmc.sampletest.popup.interpolator.JellyInterpolator;
import com.mmc.sampletest.utils.DisplayUtil;

/**
 * Created by 上海滩小马哥 on 2017/10/9.
 */

public class TestThreeFragment extends Fragment implements View.OnClickListener {

    private Button test_animator, btn_valueAnimator;
    private LinearLayout linearLayout;
    private ImageView test_vector, iv_valueAnimator;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_three, null);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayout = (LinearLayout) view.findViewById(R.id.content);
        test_animator = (Button) view.findViewById(R.id.test_animator);
        btn_valueAnimator = (Button) view.findViewById(R.id.btn_valueAnimator);
        test_vector = (ImageView) view.findViewById(R.id.test_vector);
        iv_valueAnimator = (ImageView) view.findViewById(R.id.iv_valueAnimator);

        test_animator.setOnClickListener(this);
        btn_valueAnimator.setOnClickListener(this);
        iv_valueAnimator.setOnClickListener(this);
        test_vector.setOnClickListener(this);

        //viewGroup加载view是的过度动画
        Animation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(1000);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation, 0.5F);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_RANDOM);
        linearLayout.setLayoutAnimation(layoutAnimationController);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_animator:
                PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 1f, 3f, 1f);
                PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.5f, 1f);
                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(test_animator, holder1, holder2).setDuration(2000);
                objectAnimator.setInterpolator(new JellyInterpolator());
                objectAnimator.start();
                break;
            case R.id.test_vector:
                vectorTest();
                break;
            case R.id.btn_valueAnimator:
                valueAnimatorShow();
                break;
            case R.id.iv_valueAnimator:
                valueAnimatorHide();
                break;
        }
    }

    //svg动画测试
    private void vectorTest() {
        Drawable drawable = test_vector.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }


    //valueAnimator测试
    private void valueAnimatorShow() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) animation.getAnimatedValue();
                iv_valueAnimator.getLayoutParams().height = DisplayUtil.dip2Pix(getActivity(), height);
                iv_valueAnimator.requestLayout();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                iv_valueAnimator.setVisibility(View.VISIBLE);
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        valueAnimator.start();
        iv_valueAnimator.setVisibility(View.VISIBLE);
    }

    private void valueAnimatorHide() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(100, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) animation.getAnimatedValue();
                iv_valueAnimator.getLayoutParams().height = DisplayUtil.dip2Pix(getActivity(), height);
                iv_valueAnimator.requestLayout();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                iv_valueAnimator.setVisibility(View.GONE);
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        valueAnimator.start();
    }
}
