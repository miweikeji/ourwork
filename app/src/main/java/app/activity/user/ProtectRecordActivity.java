package app.activity.user;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miwei.jzj_system.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.BaseActivity;
import app.activity.mywork.adapter.MessageAdapter;
import app.adapter.HintAdapter;
import app.adapter.ProtectRecordAdapter;
import app.entity.MessageItem;
import app.entity.ProtectRecord;
import app.entity.ProtectRecordResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Footools;
import app.utils.Config;
import app.utils.Uihelper;
import app.views.NavigationBar;

public class ProtectRecordActivity extends BaseActivity implements AbsListView.OnScrollListener  {

    private ProtectRecordAdapter adapter;
    List<ProtectRecord> items=new ArrayList<>();
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
        HttpRequest.protectlist(mActivity, p, new ICallback<ProtectRecordResult>() {
            @Override
            public void onSucceed(ProtectRecordResult result) {
                isFirstLoaded=true;
                pull_list.onRefreshComplete();
                disMissWaitingDialog();
                List<ProtectRecord> list= result.getMessage();
<<<<<<< HEAD

                if (list!=null&&list.size()>0){
                    items.addAll(list);
                    adapter = new ProtectRecordAdapter(mActivity, items);
                    listView.setAdapter(adapter);
=======
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
                        adapter = new ProtectRecordAdapter(mActivity, items);
                        pull_list.setAdapter(adapter);
                    }else {
                        adapter.notifyDataSetChanged();
                    }
>>>>>>> f41f12690ee6d6f0763fcb9bdf3597b543340381
                }

            }

            @Override
            public void onFail(String error) {
                isFirstLoaded=true;
                disMissWaitingDialog();
                pull_list.onRefreshComplete();

            }
        });

    }

    @Override
    public void initUI() {
        pull_list= (PullToRefreshListView)findViewById(R.id.listView);
        pull_list.setOnScrollListener(this);
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

    @Override
    public int onCreateMyView() {
        return R.layout.common_listview_show;
    }

    @Override
    public void initTitle(NavigationBar mBar) {

        mBar.setContexts(this);
        mBar.setTitle("保障金记录");
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (visibleItemCount+firstVisibleItem- totalItemCount>= Config.NUMBER&&isOver){
            p++;
            if(page>1&&p!=page){
                Footools.addFoot(pull_list, mActivity, inflate);
            }
            isOver=false;
            obtainData();
        }

    }
}
