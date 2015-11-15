package app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.miwei.jzj_system.R;

import java.io.InputStream;
import java.util.ArrayList;

import app.tools.ScreenUtil;
import app.utils.UserUtil;

/**
 * Created by Administrator on 2015/11/2.
 */
public class GuideActivity extends Activity {
    private ArrayList<View> mAllLayout = new ArrayList<View>();

    private int[] mDrawRes = new int[] { R.mipmap.guide_bg_one,
            R.mipmap.guide_bg_two, R.mipmap.guide_bg_three };
    private int[] mDraw = new int[] { R.mipmap.guide_hint_one,
            R.mipmap.guide_hint_two, R.mipmap.guide_hint_three };

    private int height;
    private int width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        height = ScreenUtil.getScreenWidth(this);
        width = ScreenUtil.getScreenHeight(this);

        for (int i = 0; i < mDrawRes.length; i++) {
            View layout = getLayoutInflater().inflate(R.layout.layout_guide,
                    null);


            ImageView img_guide_one_ = (ImageView) layout
                    .findViewById(R.id.img_guide_hint);

//            ViewGroup.LayoutParams params_ = img_guide_one_.getLayoutParams();
//            img_guide_one_.setLayoutParams(params_);
//
//            if (i == 0) {// 686*716
//                params_.width = width * 686 / 720;
//                params_.height = width * 716 / 720;
//                img_guide_one_.setLayoutParams(params_);
//            } else if (i == 1) {
//                params_.width = width * 618 / 720;
//                params_.height = width * 706 / 720;
//                img_guide_one_.setLayoutParams(params_);
//            } else if (i == 2) {
//                params_.width = width * 637 / 720;
//                params_.height = width * 726 / 720;
//                img_guide_one_.setLayoutParams(params_);
//            }
//            if (i < 3) {// 686*716
                Bitmap bitmap = readBitMap(getApplicationContext(),mDraw[i]);
                img_guide_one_.setImageBitmap(bitmap);

//				img_guide_one_.setBackgroundResource(mDraw[i]);
//            }
            layout.setBackgroundResource(mDrawRes[i]);
            if (i == mDrawRes.length - 1) { // 判断是否为最后一张 按钮的显示
//                img_guide_one_.setVisibility(View.INVISIBLE);
                ImageView mButton = (ImageView) layout
                        .findViewById(R.id.img_guide_start);
                mButton.setVisibility(View.VISIBLE);
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserUtil.saveIsFirst(GuideActivity.this,true);
                       startActivity(new Intent(GuideActivity.this, MainActivity.class));
                        finish();
                    }
                });
            }
            mAllLayout.add(layout);
        }


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new PagerAdapter() {
            // 类似于ListView适配器的getView
            public Object instantiateItem(View container, int position) {
                View layout = mAllLayout.get(position);
                viewPager.addView(layout);
                return layout;
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                View layout = mAllLayout.get(position);
                viewPager.removeView(layout);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return mDrawRes.length;
            }
        });

    }

    public Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }
}
