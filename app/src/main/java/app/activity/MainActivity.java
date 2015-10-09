package app.activity;

import com.miweikeij.app.R;

import app.entity.Meta;
import app.net.HttpRequest;
import app.net.ICallback;
import app.views.NavigationBar;

public class MainActivity extends BaseActivity {

    @Override
    public void obtainData() {
        HttpRequest.testHttp(mActivity, new ICallback<Meta>() {
            @Override
            public void onSucceed(Meta result) {

            }

            @Override
            public void onFail(String error) {

            }
        },"13365047950", "0");
    }

    @Override
    public void initUI() {

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_main;
    }

    @Override
    public void initTitle(NavigationBar mBar) {

    }


}
