package app.activity.user;

import android.widget.ListView;

import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.BaseActivity;
import app.adapter.ProtectRecordAdapter;
import app.entity.ProtectRecord;
import app.entity.ProtectRecordResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

public class ProtectRecordActivity extends BaseActivity {

    private ProtectRecordAdapter adapter;
    private ListView listView;
    List<ProtectRecord> items=new ArrayList<>();

    @Override
    public void obtainData() {

          showWaitingDialog();
        HttpRequest.protectlist(mActivity, 1, new ICallback<ProtectRecordResult>() {
            @Override
            public void onSucceed(ProtectRecordResult result) {
                disMissWaitingDialog();
                List<ProtectRecord> list= result.getMessage();
                if (list!=null&&list.size()>0){
                    items.addAll(list);
                    adapter = new ProtectRecordAdapter(mActivity, items);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFail(String error) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity,error);

            }
        });

    }

    @Override
    public void initUI() {
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.common_listview_show;
    }

    @Override
    public void initTitle(NavigationBar mBar) {

        mBar.setContexts(this);
        mBar.setTitle("保障金记录");
    }

}
