package app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.miweikeij.app.R;

import java.util.LinkedList;

import app.views.NavigationBar;

public abstract class BaseActivity extends FragmentActivity {

    private LinearLayout mContentView;
    public static LinkedList<Activity> sAllActivitys = new LinkedList<Activity>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        NavigationBar mBar = (NavigationBar)findViewById(R.id.navigationBar);
        initCotentView();
        initTitle(mBar);
        initUI();
        obtainData();
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


}
