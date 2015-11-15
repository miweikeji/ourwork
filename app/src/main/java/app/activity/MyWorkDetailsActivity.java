package app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.miwei.jzj_system.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

import app.activity.mywork.MyWorkDetailsAdapter;
import app.entity.CaseItem;
import app.entity.Comment;
import app.entity.Data;
import app.entity.MyWorkDetailMessage;
import app.entity.MyWorkDetailResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.utils.UserUtil;
import app.views.MyListView;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/12.
 */
public class MyWorkDetailsActivity extends BaseActivity implements
        NavigationBar.RightOnClick {


    private RatingBar ratingBarQuality;
    private RatingBar ratingBarValue;
    private TextView tv_feestyle;
    private TextView tvPrice;
    private TextView tvValue;
    private TextView tvName;
    private List<Data> data;
    private TextView tvService;
    private String workId;
    private TextView tvCraft;
    private TextView tv_commentTime;
    private MyListView listView;
    private int state;  //0为进行中，1为已完成
    private View frame_comment;
    private View frame_data;
    private DisplayImageOptions options;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //工作任务
        Intent intent = getIntent();
        workId = intent.getStringExtra("wordId");
        state = intent.getIntExtra("state", 0);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void obtainData() {

        showWaitingDialog();
        HttpRequest.myWorkDetail(this, UserUtil.getUserId(mActivity), workId, state + "", new ICallback<MyWorkDetailResult>() {
            @Override
            public void onSucceed(MyWorkDetailResult result) {
                disMissWaitingDialog();
                MyWorkDetailMessage message = result.getMessage();
                tvName.setText(message.getS_addr());
                tvService.setText(message.getContent());
                String type = message.getCharge_type();
                if ("0".equals(type)) {
                    tv_feestyle.setText("按次服务");
                } else if ("1".equals(type)) {
                    tv_feestyle.setText("按平方");
                } else if ("2".equals(type)) {
                    tv_feestyle.setText("承包价格");
                }

                tvPrice.setText(message.getW_money());
                Comment comment = message.getComment();
                if (comment != null) {
                    frame_comment.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(comment.getTime())) {
                        tv_commentTime.setText(comment.getTime());
                    }
                    tvCraft.setText(comment.getComment_craftsman_name());
                    tvValue.setText(comment.getAdvise());
                    ratingBarValue.setRating(comment.getQuality());
                    ratingBarQuality.setRating(comment.getAttitude());
                }
                List<CaseItem> list = message.getData();
                if (list.size() > 0) {
                    frame_data.setVisibility(View.VISIBLE);
                    MyWorkDetailsAdapter adater = new MyWorkDetailsAdapter(mActivity, list, options);
                    listView.setAdapter(adater);
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

        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new RoundedBitmapDisplayer(0)).build();

        frame_comment = findViewById(R.id.frame_comment);
        frame_data = findViewById(R.id.frame_data);
        listView = (MyListView) findViewById(R.id.listViewq);
        tvName = (TextView) findViewById(R.id.tv_mywork_name);
        tv_feestyle = (TextView) findViewById(R.id.tv_mywork_style);
        tvPrice = (TextView) findViewById(R.id.tv_mywork_price);
        tvService = (TextView) findViewById(R.id.tv_mywork_service);

        ratingBarQuality = (RatingBar) findViewById(R.id.ratingBar_quality);
        ratingBarValue = (RatingBar) findViewById(R.id.ratingBar_value);
        ratingBarQuality.setClickable(false);
        ratingBarQuality.setClickable(false);

        //评论模块
        tvCraft = (TextView) findViewById(R.id.tv_mywork_craft);
        tv_commentTime = (TextView) findViewById(R.id.tv_mywork_time);
        tvValue = (TextView) findViewById(R.id.tv_mywork_value);
    }

    //查看施工项目
    public void toProdDetails(View view) {
        startActivity(new Intent(this, HouseActivity.class));
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_my_work_details;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工作详情");
        mBar.setRightTwoTitle("看他主页");
        mBar.setRightOnClick(this);
    }

    @Override
    public void setRightOnClick() {
        if (!TextUtils.isEmpty(workId)) {
            CraftsmanZoneActivity.enterActivity(mActivity, Integer.parseInt(workId));
        }
    }

    public static void enterActivity(Activity activity, int craftId, int state) {

        Intent intent = new Intent(activity, MyWorkDetailsActivity.class);
        intent.putExtra("wordId", craftId);
        intent.putExtra("state", craftId);
        activity.startActivity(intent);

    }
}