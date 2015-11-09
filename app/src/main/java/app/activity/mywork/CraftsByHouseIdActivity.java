package app.activity.mywork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import app.activity.BaseActivity;
import app.activity.mywork.adapter.CraftsAdapter;
import app.entity.Allcrafts;
import app.entity.CraftsByHouseIdResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.CircleBitmapDisplayer;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/11/8.
 */
public class CraftsByHouseIdActivity extends BaseActivity {
    private CraftsAdapter adapter;
    private ListView listView;
    List<Allcrafts> items = new ArrayList<>();
    private DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.test).cacheInMemory(true).cacheOnDisk(true)
                .displayer(new CircleBitmapDisplayer()).build();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void obtainData() {

        showWaitingDialog();
        HttpRequest.getCraftsByHouseId(mActivity, "753665", new ICallback<CraftsByHouseIdResult>() {
            @Override
            public void onSucceed(CraftsByHouseIdResult result) {
                disMissWaitingDialog();
                items = result.getCraftsList();
                if (items != null && items.size() > 0) {
                    adapter = new CraftsAdapter(mActivity, items, imageLoader, options);
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

                Allcrafts allcrafts = items.get(position);
                String craftId = allcrafts.getId();
                String craftName = allcrafts.getName();
                Intent intent = new Intent();
                intent.putExtra("craftId", craftId);
                intent.putExtra("craftName", craftName);
                setResult(1, intent);
                finish();
            }
        });


    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_alllistview;
    }

    @Override
    public void initTitle(NavigationBar mBar) {

        mBar.setContexts(this);
        mBar.setTitle("所有工匠");

    }
}
