package com.mmc.sampletest.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class DisplayUtil {

	public static int dip2Pix(Context context, int dip) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dip, context.getResources().getDisplayMetrics());
	}

	public static int dip2Sp(Context context, int dip) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dip,
				context.getResources().getDisplayMetrics());
	}

	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	public static Bitmap drawable2Bitmap(Drawable drawable) {
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);
		return bitmap;
	}

	public static Drawable bitmap2Drawable(Context context, Bitmap bitmap) {
		return new BitmapDrawable(context.getResources(), bitmap);
	}

	/**
	 * 灰度图
	 */
	public static Bitmap toGrayscaleBitmap(Bitmap bmpOriginal) {
		int height = bmpOriginal.getHeight();
		int width = bmpOriginal.getWidth();
		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
				Config.ARGB_8888);
		Canvas c = new Canvas(bmpGrayscale);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmpOriginal, 0, 0, paint);
		return bmpGrayscale;
	}

	public static Drawable toGrayscaleDrawable(Context context,
                                               Drawable drawable) {
		return bitmap2Drawable(context,
				toGrayscaleBitmap(drawable2Bitmap(drawable)));
	}

	/**
	 * 圆角
	 */
	public static Bitmap toRoundCornerBitmap(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	public static Drawable toRoundCornerDrawable(Context context,
                                                 Drawable drawable, int pix) {
		return bitmap2Drawable(context,
				toRoundCornerBitmap(drawable2Bitmap(drawable), pix));
	}

	/**
	 * 灰度圆角
	 */
	public static Bitmap toRoundGrayscaleBitmap(Bitmap bmpOriginal, int pixels) {
		return toRoundCornerBitmap(toGrayscaleBitmap(bmpOriginal), pixels);
	}

	public static Drawable toRoundGrayscaleDrawable(Context context,
                                                    Drawable drawable, int pix) {
		return bitmap2Drawable(context,
				toRoundCornerBitmap(drawable2Bitmap(drawable), pix));
	}

	public static Drawable getDrawablesMerge(Drawable[] drawables) {
		int length = drawables.length;
		int repeat = length - 1;
		int leftGap = 0;
		int rightGap = 0;
		int topGap = 0;
		int bottomGap = 0;
		LayerDrawable layerDrawable = new LayerDrawable(drawables);
		for (int i = 0; i < length; i++) {
			Drawable drawable = drawables[i];
			int w = drawable.getIntrinsicWidth();
			rightGap = (repeat - i) * w;
			layerDrawable
					.setLayerInset(i, leftGap, topGap, rightGap, bottomGap);
			leftGap += w;
		}
		return layerDrawable;
	}

	public static Drawable getDrawablesMerge(Drawable[] drawables,
                                             Drawable mergeBackground) {
		int length = drawables.length;
		Drawable[] newDrawables = new Drawable[length + 1];
		newDrawables[0] = mergeBackground;
		System.arraycopy(drawables, 0, newDrawables, 1, length);
		drawables = newDrawables;
		length = drawables.length;
		int repeat = length - 1;
		Rect rect = new Rect();
		mergeBackground.getPadding(rect);
		int leftGap = 0;
		int rightGap = 0;
		int topGap = rect.top;
		int bottomGap = rect.bottom;
		LayerDrawable layerDrawable = new LayerDrawable(drawables);
		for (int i = 0; i < length; i++) {
			if (i == 0)
				continue;
			Drawable drawable = drawables[i];
			int w = drawable.getIntrinsicWidth();
			rightGap = (repeat - i) * w;

			// only first had left padding
			if (i == 1) {
				leftGap += rect.left;
			}
			// only end had right padding
			if (i == length - 1) {
				rightGap += rect.right;
			}

			layerDrawable
					.setLayerInset(i, leftGap, topGap, rightGap, bottomGap);

			leftGap += w;
		}
		return layerDrawable;
	}

	public interface onCompleteListener{
		void onComplete();
	}

	public static void snapAnimotion(Context context, final ViewGroup contanierView, View srcView, View dstView, Bitmap bitmap, final onCompleteListener listener) {
		int[] srcLocation = new int[2];
		srcView.getLocationOnScreen(srcLocation);
		int srcLeft = srcLocation[0];
		int srcTop = srcLocation[1];
		int srcWidth = srcView.getWidth();
		int srcHeight = srcView.getHeight();

		int[] dstLocation = new int[2];
		dstView.getLocationOnScreen(dstLocation);
		int dstLeft = dstLocation[0];
		int dstTop = dstLocation[1];
		int dstWidth = dstView.getWidth();
		int dstHeight = dstView.getHeight();

		final ImageView imageView = new ImageView(context);
		imageView.setX(srcLeft);
		imageView.setY(srcTop + SizeUtil.dp2px(context, SizeUtil.getTiltleHeight((Activity) context)));
		imageView.setImageBitmap(bitmap);
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(srcWidth, srcHeight);
		contanierView.addView(imageView, layoutParams);

//        Animation scaleAnimation = new ScaleAnimation(1.0f, (float)dstWidth/srcWidth, 1.0f, (float)dstHeight/srcHeight, (float)dstLeft, (float)dstTop);
		Animation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, (float)dstLeft, (float)dstTop + SizeUtil.dp2px(context, SizeUtil.getTiltleHeight((Activity) context)));
		scaleAnimation.setDuration(500);
		scaleAnimation.setInterpolator(context, android.R.anim.decelerate_interpolator);//设置动画插入器
		scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				contanierView.removeView(imageView);
				listener.onComplete();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		imageView.startAnimation(scaleAnimation);
	}
}