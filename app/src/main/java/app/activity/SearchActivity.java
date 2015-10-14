package app.activity;

import android.view.View;
import android.widget.TextView;

import com.miweikeij.app.R;

import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/14.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        TextView search = (TextView)findViewById(R.id.tv_search);
        search.setOnClickListener(this);

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_search;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setBarInVisibile();
    }

    public void back(View view){
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_search:

                break;
        }
    }
}
