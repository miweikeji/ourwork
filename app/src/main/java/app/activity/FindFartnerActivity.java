package app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.miweikeij.app.R;

import app.views.NavigationBar;

public class FindFartnerActivity extends BaseActivity {


    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_find_fartner;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("找搭档");
    }
}
