package app.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


import app.activity.BaseActivity;
import app.tools.ScreenUtil;
import app.views.ViewpagerIndicator;

/**
 * Created by Administrator on 2015/10/8.
 */
public class JobOpportunityFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private View layout;
    private ViewpagerIndicator indicator;
    private FragmentManager fm;
    private ViewPager pager;
    private boolean isDragging;
    private Handler mhandler;
    private ImageLoader imageLoader;
    private String[] url = new String[]{"http://mss-product.oss-cn-shenzhen.aliyuncs.com/picture/advert/p720/A720_1442919759965.jpg",
            "http://mss-product.oss-cn-shenzhen.aliyuncs.com/picture/advert/p720/A720_1442222369280.jpg",
            "http://mss-product.oss-cn-shenzhen.aliyuncs.com/picture/advert/p720/A720_1442919679412.jpg"};

    JobOpportunityFragmentDelegate delegate;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_water:
                delegate.getCraftsmanType("水电工");
                break;
            case R.id.rl_muddy:
                delegate.getCraftsmanType("泥水工");
                break;
            case R.id.rl_carpenter:
                delegate.getCraftsmanType("木工");
                break;
            case R.id.rl_painter:
                delegate.getCraftsmanType("油漆工");
                break;
            case R.id.rl_door:
                delegate.getCraftsmanType("门窗安装工");
                break;
            case R.id.rl_porter:
                delegate.getCraftsmanType("敲打搬运工");
                break;
        }
    }

    public interface JobOpportunityFragmentDelegate {
        public void getCraftsmanType(String type);
    }
    public void setJobOpportunityFragmentDelegate(JobOpportunityFragmentDelegate delegate){
        this.delegate = delegate;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_job_opportunity, null);
        imageLoader = ImageLoader.getInstance();
        initUI();
        initViewPager();
        autoScroll();
        return layout;
    }


    private void initUI() {
        int px = ScreenUtil.instance(getActivity()).dip2px(8);
        int width = ScreenUtil.instance(getActivity()).getScreenWidth();
        RelativeLayout rl_hight_one=(RelativeLayout)layout.findViewById(R.id.rl_hight_one);
        ViewGroup.LayoutParams params_one =rl_hight_one.getLayoutParams();
        params_one.height = (width-px)/2;
        rl_hight_one.setLayoutParams(params_one);
        RelativeLayout rl_hight_two=(RelativeLayout)layout.findViewById(R.id.rl_hight_two);
        ViewGroup.LayoutParams params_two =rl_hight_two.getLayoutParams();
        params_two.height = (width-px)/2;
        rl_hight_two.setLayoutParams(params_two);
        pager = (ViewPager)layout.findViewById(R.id.pager);
        indicator = (ViewpagerIndicator) layout.findViewById(R.id.viewpagerIndicator1);

        RelativeLayout rl_water = (RelativeLayout)layout.findViewById(R.id.rl_water);
        RelativeLayout rl_muddy = (RelativeLayout)layout.findViewById(R.id.rl_muddy);
        RelativeLayout rl_carpenter = (RelativeLayout)layout.findViewById(R.id.rl_carpenter);
        RelativeLayout rl_painter = (RelativeLayout)layout.findViewById(R.id.rl_painter);
        RelativeLayout rl_door = (RelativeLayout)layout.findViewById(R.id.rl_door);
        RelativeLayout rl_porter = (RelativeLayout)layout.findViewById(R.id.rl_porter);
        rl_porter.setOnClickListener(this);
        rl_water.setOnClickListener(this);
        rl_painter.setOnClickListener(this);
        rl_carpenter.setOnClickListener(this);
        rl_muddy.setOnClickListener(this);
        rl_door.setOnClickListener(this);
    }
    private void initViewPager() {
        fm = getFragmentManager();
        pager.setAdapter(new BannerAdapter(fm));
        pager.setOnPageChangeListener(this);
        pager.setCurrentItem(70000);
    }

    private void autoScroll() {
        if (mhandler == null) {
            mhandler = new Handler();

        }
        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isDragging) {
                    int item = pager.getCurrentItem() + 1;
                    pager.setCurrentItem(item);
                }
                mhandler.postDelayed(this, 5000);
            }
        }, 5000);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        indicator.setPointVISE(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                isDragging = true;
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                isDragging = false;
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                break;
            default:
                break;
        }
    }


    private final class BannerAdapter extends FragmentPagerAdapter{
        private static final int MAX_LEN = 100000;
        public BannerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            BannerFragment fragment = new BannerFragment();
            int index = (position) % url.length;
            fragment.setUrl(url[index]);
            fragment.setImageLoader(imageLoader);
            fragment.setOption(BaseActivity.options);
            return fragment;
        }

        @Override
        public int getCount() {
            return MAX_LEN;
        }
    }
}
