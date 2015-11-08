package app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miweikeij.app.R;

import app.entity.CraGroup;
import app.entity.CraGroupResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

public class CraftsmanGroupActivity extends BaseActivity implements View.OnClickListener {


    private TextView tv_my_craftsman_group;
    private TextView tv_creat_time;
    private TextView tv_group_num;
    private TextView constructionType;
    private TextView tv_style;
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

        RelativeLayout rl_owner_reservation = (RelativeLayout) findViewById(R.id.rl_owner_reservation);
        rl_owner_reservation.setOnClickListener(this);
        RelativeLayout rl_group_members = (RelativeLayout) findViewById(R.id.rl_group_members);
        rl_group_members.setOnClickListener(this);

        tv_my_craftsman_group = (TextView)findViewById(R.id.tv_my_craftsman_group);
        tv_creat_time = (TextView)findViewById(R.id.tv_creat_time);
        tv_group_num = (TextView)findViewById(R.id.tv_group_num);
        constructionType = (TextView)findViewById(R.id.tv_Construction_type);
        tv_style = (TextView)findViewById(R.id.tv_style);


        netWorkData();
    }

    private void netWorkData() {
        HttpRequest.craftsmanGroupHttp(this, "106", new ICallback<CraGroupResult>() {
            @Override
            public void onSucceed(CraGroupResult result) {
                CraGroup group = result.getGroup();
                tv_style.setText("装修风格："+group.getExpert());
                constructionType.setText("施工方式："+group.getStyle());
                tv_group_num.setText("工匠队伍："+group.getCount()+"人");
                tv_creat_time.setText("" + group.getCreate_time());
                tv_my_craftsman_group.setText(group.getName() + "工匠班组");

            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(CraftsmanGroupActivity.this,error);
            }
        });
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_craftsman_group;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工匠组班");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_owner_reservation:
                startActivity(new Intent(this,OwnerReservationActivity.class));
                break;
            case R.id.rl_group_members:
                startActivity(new Intent(this,GroupMembersActivity.class));
                break;
        }
    }
}
