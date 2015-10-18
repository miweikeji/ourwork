package app.activity.user;

import android.widget.ListView;

import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.BaseActivity;
import app.adapter.ProtectRecordAdapter;
import app.entity.ProtectRecord;
import app.views.NavigationBar;

public class ProtectRecordActivity extends BaseActivity {

    private ProtectRecordAdapter adapter;
    private ListView listView;
    List<ProtectRecord> items=new ArrayList<>();

    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        listView = (ListView) findViewById(R.id.listView);

        for (int i = 0; i < 10; i++) {
            ProtectRecord protectRecord=new ProtectRecord();
            items.add(protectRecord);
        }
        adapter = new ProtectRecordAdapter(mActivity, items);
        listView.setAdapter(adapter);

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
