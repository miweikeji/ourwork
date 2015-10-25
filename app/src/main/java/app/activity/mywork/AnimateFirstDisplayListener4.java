package app.activity.mywork;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AlphabetIndexer;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class AnimateFirstDisplayListener4 extends SimpleImageLoadingListener {
	static final List<String> displayedImages = Collections
			.synchronizedList(new LinkedList<String>());

	@Override
	public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
		if (loadedImage != null) {
			ImageView imageView = (ImageView) view;
			imageView.setScaleType(ScaleType.CENTER_CROP);
			boolean firstDisplay = !displayedImages.contains(imageUri);
			if (firstDisplay) {
				Animation alphaAnim = new AlphaAnimation(0.5f, 1.0f);
				alphaAnim.setDuration(1000);
				imageView.startAnimation(alphaAnim);
				displayedImages.add(imageUri);
			}
			
			
//			if (firstDisplay) {
//				FadeInBitmapDisplayer.animate(imageView, 500);
//				displayedImages.add(imageUri);
//			}
		}
	}
	
	@Override
	public void onLoadingStarted(String imageUri, View view) {
		ImageView imageView = (ImageView)view;
		imageView.setScaleType(ScaleType.CENTER_CROP);
	}
}