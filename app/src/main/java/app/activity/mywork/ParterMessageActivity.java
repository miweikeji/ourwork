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
import app.entity.MessageItem;
import app.entity.MessageResult;
import app.entity.ProtectRecord;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/27.
 */
public class ParterMessageActivity extends BaseActivity {

    private MessageAdapter adapter;
    private ListView listView;
    List<MessageItem> items = new ArrayList<>();
    private int page;

    @Override
    public void obtainData() {

        showWaitingDialog();
        page = 1;
        HttpRequest.getMessages(mActivity, "101", page, new ICallback<MessageResult>() {
            @Override
            public void onSucceed(MessageResult result) {
                disMissWaitingDialog();
                items = result.getMessages();
                if (items != null && items.size() > 0) {
                    adapter = new MessageAdapter(mActivity, items);
                    listView.setAdapter(adapter);
                }

            }

            @Override
            public void onFail(String error) {

                disMissWaitingDialog();
                Uihelper.showToast(mActivity, error);
            }
        });
    }

    @Override
    public void initUI() {


        listView = (ListView) findViewById(R.id.listView);

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
