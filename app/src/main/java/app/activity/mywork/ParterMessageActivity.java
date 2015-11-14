package app.activity.mywork;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.BaseActivity;
import app.activity.SystemMessageActivity;
import app.activity.mywork.adapter.MessageAdapter;
import app.adapter.ClassMonitorAdapter;
import app.adapter.HintAdapter;
import app.entity.MessageItem;
import app.entity.MessageResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Footools;
import app.utils.Config;
import app.utils.Uihelper;
import app.utils.UserUtil;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/27.
 */
public class ParterMessageActivity extends BaseActivity implements AbsListView.OnScrollListener {

    private MessageAdapter adapter;
    List<MessageItem> items = new ArrayList<>();
    private int p=1;
    private PullToRefreshListView pull_list;
    private int page;
    private boolean isOver;
    private boolean isFirstLoaded;
    private View inflate;

    @Override
    public void obtainData() {
        if (!isFirstLoaded){
            showWaitingDialog();
        }
        HttpRequest.getMessages(mActivity, UserUtil.getUserId(mActivity), p, new ICallback<MessageResult>() {
            @Override
            public void onSucceed(MessageResult result) {
                isFirstLoaded=true;
                pull_list.onRefreshComplete();
                disMissWaitingDialog();
                List<MessageItem>  list = result.getMessages();
                page=result.getPage();
                if(page==0){
                    isOver = false;
                    HintAdapter hintAdapter = new HintAdapter(mActivity);
                    pull_list.getRefreshableView().setDividerHeight(0);
                    pull_list.setAdapter(hintAdapter);
                }else {

                    if(p<=page){
                        if(p<=page-1){
                            isOver = true;
                        }
                        items.addAll(list);
                    }else {
                        isOver = false;
                        Footools.removeFoot(pull_list, mActivity, inflate);
                    }
                    if(p==1){
                        adapter = new MessageAdapter(mActivity, items);
                        pull_list.setAdapter(adapter);
                    }else {
                        adapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onFail(String error) {
                isFirstLoaded=true;
                disMissWaitingDialog();
                pull_list.onRefreshComplete();
                Uihelper.showToast(mActivity, error);
            }
        });
    }

    @Override
    public void initUI() {

        pull_list= (PullToRefreshListView)findViewById(R.id.listView);
        pull_list.setOnScrollListener(this);
        pull_list.setOnItemClickListener(onitemClick);
        pull_list.setMode(PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        inflate = getLayoutInflater().inflate(R.layout.footview, null);

        pull_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                p=1;
                items.clear();
                if (adapter!=null){

                    adapter.notifyDataSetChanged();
                }
                obtainData();
            }
        });


    }

    AdapterView.OnItemClickListener onitemClick= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MessageItem messageItem = items.get(position);
            String enterDetail = messageItem.getEnterDetail();
            if (!TextUtils.isEmpty(enterDetail)) {
                switch (enterDetail) {
//        0 表示点击详情进入“有人申请加入”界面
//        1表示点击详情进入“申请接单成功”界面
//        2表示点击详情进入“申请接单失败”界面
//        3表示点击详情进入“邀请施工”界面
//        4表示点击详情进入“邀请施工人员成功”界面
//        5表示点击详情进入“邀请施工人员失败”界面
//        6表示点击详情进入“业主预约”界面
//        7表示点击详情进入“系统消息”界面
                    case "0":
                        MessageA_Activity.enterActivity(mActivity, messageItem.getWorkId(), 1, messageItem.getId());
                        break;
                    case "1":
                        MessageA_Activity.enterActivity(mActivity, messageItem.getWorkId(), 1, messageItem.getId());
                        break;
                    case "2":
                        MessageA_Activity.enterActivity(mActivity, messageItem.getWorkId(), 2, messageItem.getId());
                        break;
                    case "3":
                        MessageB_Activity.enterActivity(mActivity, messageItem.getWorkId(), 3, messageItem.getId());
                        break;
                    case "4":
                        MessageB_Activity.enterActivity(mActivity, messageItem.getWorkId(), 3, messageItem.getId());
                        break;
                    case "5":
                        MessageB_Activity.enterActivity(mActivity, messageItem.getWorkId(), 3, messageItem.getId());
                        break;
                    case "6":

                        Intent intent = new Intent(mActivity, YuyueDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("messageItem", messageItem);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        break;
                    case "7":

                        Intent intent_system = new Intent(mActivity, SystemMessageActivity.class);
                        startActivity(intent_system);

                        break;
                    default:
                        break;

                }
            }

        }
    };
    @Override
    public int onCreateMyView() {
        return R.layout.activity_parter_message;
    }

    @Override
    public void initTitle(NavigationBar mBar) {

        mBar.setContexts(this);
        mBar.setTitle("搭档反馈消息");

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (visibleItemCount+firstVisibleItem- totalItemCount>=Config.NUMBER&&isOver){
            p++;
            if(page>1&&p!=page){
                Footools.addFoot(pull_list, mActivity, inflate);
            }
            isOver=false;
            obtainData();
        }

    }
}
