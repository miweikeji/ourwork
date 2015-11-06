package app.activity;

import com.miweikeij.app.R;

import java.util.List;

import app.entity.Info;
import app.entity.JsonDataResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/11/6.
 */
public class ChangeTasksActivity extends BaseActivity {
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

        HttpRequest.getDetailTask(this, "753666", new ICallback<JsonDataResult>() {
            @Override
            public void onSucceed(JsonDataResult result) {
                List<Info> info = result.getInfo();

            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(ChangeTasksActivity.this,error);
            }
        });
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_change_tasks;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("安排施工任务");
    }
}
