package app.activity.mywork;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.BaseActivity;
import app.activity.mywork.adapter.MessageAdapter;
import app.adapter.ProtectRecordAdapter;
import app.entity.Message;
import app.entity.ProtectRecord;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/27.
 */
public class ParterMessageActivity extends BaseActivity {

    private MessageAdapter adapter;
    private ListView listView;
    List<Message> items=new ArrayList<>();
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

        listView = (ListView) findViewById(R.id.listView);

        for (int i = 0; i < 10; i++) {
            Message message=new Message();
            items.add(message);
        }
        adapter = new MessageAdapter(mActivity, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //消息详情
            }
        });


    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_parter_message;
    }

    @Override
    public void initTitle(NavigationBar mBar) {

        mBar.setContexts(this);
        mBar.setTitle("搭档反馈消息");

    }
}
