package app.activity;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.miweikeij.app.R;


import app.views.NavigationBar;

public class CraftsmanFriendsActivity extends BaseActivity {


    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_craftsman_friends;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setBarInVisibile();
    }
}
