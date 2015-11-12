package app.activity.mywork;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miweikeij.app.R;

import app.activity.BaseActivity;
import app.activity.HouseActivity;
import app.dialog.DialogTools;
import app.entity.HousesByLyf;
import app.entity.MessageItem;
import app.entity.Meta;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.TimeTools;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/18.
 */
public class YuyueDetailsActivity extends BaseActivity implements NavigationBar.RightOnClick, View.OnClickListener, DialogTools.DialogOnClickListens {


    private MessageItem messageItem;
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        messageItem = (MessageItem) getIntent().getSerializableExtra("messageItem");

        RelativeLayout rl_house = (RelativeLayout) findViewById(R.id.rl_house);
        rl_house.setOnClickListener(this);
        Button btn_refuse = (Button)findViewById(R.id.btn_refuse);
        Button btn_sure = (Button)findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(this);
        btn_refuse.setOnClickListener(this);

        TextView tv_creat_time = (TextView)findViewById(R.id.tv_creat_time);
        TextView tv_status_ = (TextView)findViewById(R.id.tv_status_);
        TextView tv_content = (TextView)findViewById(R.id.tv_content);
        TextView tv_time = (TextView) findViewById(R.id.tv_time);

        TextView tv_name= (TextView)findViewById(R.id.tv_name);
        TextView tv_status= (TextView)findViewById(R.id.tv_status);
        TextView tv_type= (TextView)findViewById(R.id.tv_type);
        TextView tv_area= (TextView)findViewById(R.id.tv_area);
        TextView tv_style= (TextView)findViewById(R.id.tv_style);
        TextView tv_mode= (TextView)findViewById(R.id.tv_mode);
        TextView tv_total_price= (TextView)findViewById(R.id.tv_total_price);
        tv_name.setText(messageItem.getHouse_name());
        tv_type.setText(" "+messageItem.getHouse_type()+" |");
        tv_area.setText(" "+messageItem.getHouse_area()+"平 |");
        tv_style.setText(" "+messageItem.getHouse_style()+" |");
        tv_mode.setText(" "+messageItem.getCraft_mode());
        tv_total_price.setText("￥"+messageItem.getHouse_total_price());
        if (!TextUtils.isEmpty(messageItem.getAddTime())){
            tv_creat_time.setText(TimeTools.longToDateStr(Double.parseDouble(messageItem.getAddTime())));
        }
//
//        if("1".equals(state)||"2".equals(state)){
//
//        }else if("3".equals(state)){
//            tv_status_.setText("量房");
//        }else if("4".equals(state)){
//            tv_status_.setText("量房");
//        }else if("5".equals(state)){
//            tv_status_.setText("婉拒");
////            tv_content.setText();
//            btn_sure.setVisibility(View.GONE);
//        }

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_reservation_details;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("预约详情");
        mBar.setRightImg(R.drawable.phone);
        mBar.setRightOnClick(this);
    }

    @Override
    public void setRightOnClick() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_refuse:
                DialogTools.refuseShow(this).show();
                DialogTools.setDialogOnClick(this);
                break;
            case R.id.btn_sure:
                accept();
                break;
            case R.id.rl_house:
                startActivity(new Intent(this,HouseActivity.class));
                break;
        }
    }

    private void accept() {

        String houseId = "753665";
        String ownerId = "458763";
        String craftsId = "100";
        String craftsName = "思明";
        String yyTime = "2015-10-22";
        HttpRequest.acceptAppointmentLyf(YuyueDetailsActivity.this, houseId,
                ownerId, craftsId, craftsName, yyTime, new ICallback<Meta>() {
                    @Override
                    public void onSucceed(Meta result) {
                        Uihelper.showToast(YuyueDetailsActivity.this, result.getMsg());
                    }

                    @Override
                    public void onFail(String error) {
                        Uihelper.showToast(YuyueDetailsActivity.this, error);
                    }
                });

    }

    @Override
    public void onDialogClick() {
        //确定婉拒
        refuse();
    }

    private void refuse() {
        String houseId = "753666";
        String ownerId = "425988";
        String craftsId = "100";
        String craftsName = "思明";
        String yyTime = "2015-10-22";
        HttpRequest.refuseAppointmentLyf(YuyueDetailsActivity.this, houseId,
                ownerId,craftsId, craftsName, yyTime, new ICallback<Meta>() {
            @Override
            public void onSucceed(Meta result) {
                Uihelper.showToast(YuyueDetailsActivity.this,result.getMsg());
            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(YuyueDetailsActivity.this,error);
            }
        });
    }
}
