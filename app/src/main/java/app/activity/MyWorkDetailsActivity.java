package app.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.miweikeij.app.R;

import java.util.List;

import app.entity.Comment;
import app.entity.Data;
import app.entity.MyWorkDetailMessage;
import app.entity.MyWorkDetailResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/12.
 */
public class
        MyWorkDetailsActivity extends BaseActivity implements
        NavigationBar.RightOnClick {


    private LinearLayout linearDuty;
    private RecyclerView recyclerView;
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

    @Override
    public void obtainData() {

        View item = LayoutInflater.from(mActivity).inflate(R.layout.item_person_shigongrenwu, null);
        linearDuty.addView(item);
        showWaitingDialog();
        HttpRequest.myWorkDetail(this, "101", "133", "1", new ICallback<MyWorkDetailResult>() {
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
                    if (!TextUtils.isEmpty(comment.getTime())) {

                        tv_commentTime.setText(comment.getTime());
                    }
                    tvCraft.setText(comment.getComment_craftsman_name());
                    tvValue.setText(comment.getAdvise());
                    ratingBarValue.setRating(comment.getQuality());
                    ratingBarQuality.setRating(comment.getAttitude());


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

        workId = getIntent().getStringExtra("workId");

        //工作任务
        linearDuty = (LinearLayout) findViewById(R.id.linear_shigongrenwu);
        linearDuty.setOrientation(LinearLayout.VERTICAL);

        tvName = (TextView) findViewById(R.id.tv_mywork_name);
        tv_feestyle = (TextView) findViewById(R.id.tv_mywork_style);
        tvPrice = (TextView) findViewById(R.id.tv_mywork_price);
        tvService = (TextView) findViewById(R.id.tv_mywork_service);

        ratingBarQuality = (RatingBar) findViewById(R.id.ratingBar_quality);
        ratingBarValue = (RatingBar) findViewById(R.id.ratingBar_value);

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
        startActivity(new Intent(MyWorkDetailsActivity.this, CraftsmanZoneActivity.class));
    }


}
