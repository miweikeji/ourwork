package app.activity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.LinkedList;

import app.views.NavigationBar;
import app.views.ProgressDialogView;

public abstract class BaseActivity extends FragmentActivity {

    private LinearLayout mContentView;
    public static DisplayImageOptions options;
    public static LinkedList<Activity> sAllActivitys = new LinkedList<Activity>();
    public Activity mActivity;
    public  NavigationBar mBar;
    private Dialog mWaitingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mActivity=this;
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new RoundedBitmapDisplayer(0)).build();
        mBar = (NavigationBar)findViewById(R.id.navigationBar);
        mWaitingDialog = ProgressDialogView.create(mActivity);
        initCotentView();
        obtainData();
        initTitle(mBar);
        initUI();
        sAllActivitys.add(this);
    }

    private void initCotentView() {
        mContentView = (LinearLayout) findViewById(R.id.content);
        if (onCreateMyView() == 0) return;
        View contentView = LayoutInflater.from(this).inflate(onCreateMyView(), null);
        LinearLayout.LayoutParams lpContent = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(lpContent);
        mContentView.addView(contentView);
    }

    public abstract void obtainData();

    public abstract void initUI();

    public abstract int onCreateMyView();

    public abstract void initTitle(NavigationBar mBar);
    /**
     * 显示 loading 对话框
     */
    protected void showWaitingDialog() {
        if(mWaitingDialog != null) {
            mWaitingDialog.show();
        }
    }

    /**
     * 隐藏 loading 对话框
     */
    protected void disMissWaitingDialog() {
        if(mWaitingDialog != null && mWaitingDialog.isShowing()) {
            mWaitingDialog.dismiss();
        }
    }

}
