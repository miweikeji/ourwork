package app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miweikeij.app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.entity.Meta;
import app.entity.Time;
import app.tools.TimeTools;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/11/3.
 */
public class ServerTimeActivity extends BaseActivity implements View.OnClickListener {

    private  int time_day;
    private String case_type;
    private TextView tv_time1;
    private TextView tv_time2;
    private TextView tv_time3;
    private TextView tv_time4;
    private TextView tv_time5;
    private TextView tv_time6;
    private TextView tv_time7;
    private ImageView img_am1;
    private ImageView img_am2;
    private ImageView img_am3;
    private ImageView img_am4;
    private ImageView img_am5;
    private ImageView img_am6;
    private ImageView img_am7;
    private ImageView img_pm1;
    private ImageView img_pm2;
    private ImageView img_pm3;
    private ImageView img_pm4;
    private ImageView img_pm5;
    private ImageView img_pm6;
    private ImageView img_pm7;

    private int group;
    private int mark;
    private Date d1;
    private int remainder;
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        Intent intent = getIntent();
        time_day = intent.getIntExtra("time_day", 0)-1;
        case_type = intent.getStringExtra("CASE_TYPE");

        if(time_day<8){
            group=1;
        }else {
            group=  time_day/7;
            remainder=time_day%7;
            group++;
        }

        tv_time1 = (TextView) findViewById(R.id.tv_time1);
        tv_time2 = (TextView) findViewById(R.id.tv_time2);
        tv_time3 = (TextView) findViewById(R.id.tv_time3);
        tv_time4 = (TextView) findViewById(R.id.tv_time4);
        tv_time5 = (TextView) findViewById(R.id.tv_time5);
        tv_time6 = (TextView) findViewById(R.id.tv_time6);
        tv_time7 = (TextView) findViewById(R.id.tv_time7);
        SimpleDateFormat sDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String    date    =    sDateFormat.format(new java.util.Date());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            d1=sdf.parse(date);
            if(time_day<8){
                if(time_day==1){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                }
                if(time_day==2){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                }
                if(time_day==3){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                }
                if(time_day==4){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                }
                if(time_day==5){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                    tv_time5.setText(TimeTools.getDayTime(d1,5));
                }
                if(time_day==6){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                    tv_time5.setText(TimeTools.getDayTime(d1,5));
                    tv_time6.setText(TimeTools.getDayTime(d1,6));
                }
                if(time_day==7){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                    tv_time5.setText(TimeTools.getDayTime(d1,5));
                    tv_time6.setText(TimeTools.getDayTime(d1,6));
                    tv_time7.setText(TimeTools.getDayTime(d1,7));
                }
            }else {
                tv_time1.setText(TimeTools.getDayTime(d1,1));
                tv_time2.setText(TimeTools.getDayTime(d1,2));
                tv_time3.setText(TimeTools.getDayTime(d1,3));
                tv_time4.setText(TimeTools.getDayTime(d1,4));
                tv_time5.setText(TimeTools.getDayTime(d1,5));
                tv_time6.setText(TimeTools.getDayTime(d1,6));
                tv_time7.setText(TimeTools.getDayTime(d1,7));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }















         img_am1 = (ImageView) findViewById(R.id.img_am1);
         img_am2 = (ImageView) findViewById(R.id.img_am2);
         img_am3 = (ImageView) findViewById(R.id.img_am3);
         img_am4 = (ImageView) findViewById(R.id.img_am4);
         img_am5 = (ImageView) findViewById(R.id.img_am5);
         img_am6 = (ImageView) findViewById(R.id.img_am6);
         img_am7 = (ImageView) findViewById(R.id.img_am7);

         img_pm1 = (ImageView) findViewById(R.id.img_pm1);
         img_pm2 = (ImageView) findViewById(R.id.img_pm2);
         img_pm3 = (ImageView) findViewById(R.id.img_pm3);
         img_pm4 = (ImageView) findViewById(R.id.img_pm4);
         img_pm5 = (ImageView) findViewById(R.id.img_pm5);
         img_pm6 = (ImageView) findViewById(R.id.img_pm6);
         img_pm7 = (ImageView) findViewById(R.id.img_pm7);

        RelativeLayout rl_1_left = (RelativeLayout) findViewById(R.id.rl_1_left);
        RelativeLayout rl_2_left = (RelativeLayout) findViewById(R.id.rl_2_left);
        RelativeLayout rl_3_left = (RelativeLayout) findViewById(R.id.rl_3_left);
        RelativeLayout rl_4_left = (RelativeLayout) findViewById(R.id.rl_4_left);
        RelativeLayout rl_5_left = (RelativeLayout) findViewById(R.id.rl_5_left);
        RelativeLayout rl_6_left = (RelativeLayout) findViewById(R.id.rl_6_left);
        RelativeLayout rl_7_left = (RelativeLayout) findViewById(R.id.rl_7_left);

        RelativeLayout rl_1_right = (RelativeLayout) findViewById(R.id.rl_1_right);
        RelativeLayout rl_2_right = (RelativeLayout) findViewById(R.id.rl_2_right);
        RelativeLayout rl_3_right = (RelativeLayout) findViewById(R.id.rl_3_right);
        RelativeLayout rl_4_right = (RelativeLayout) findViewById(R.id.rl_4_right);
        RelativeLayout rl_5_right = (RelativeLayout) findViewById(R.id.rl_5_right);
        RelativeLayout rl_6_right = (RelativeLayout) findViewById(R.id.rl_6_right);
        RelativeLayout rl_7_right = (RelativeLayout) findViewById(R.id.rl_7_right);

        rl_1_left.setOnClickListener(this);
        rl_2_left.setOnClickListener(this);
        rl_3_left.setOnClickListener(this);
        rl_4_left.setOnClickListener(this);
        rl_5_left.setOnClickListener(this);
        rl_6_left.setOnClickListener(this);
        rl_7_left.setOnClickListener(this);

        rl_1_right.setOnClickListener(this);
        rl_2_right.setOnClickListener(this);
        rl_3_right.setOnClickListener(this);
        rl_4_right.setOnClickListener(this);
        rl_5_right.setOnClickListener(this);
        rl_6_right.setOnClickListener(this);
        rl_7_right.setOnClickListener(this);

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_server_time;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("服务时间");

    }

    public void addComplete(View v){
         Map<String,List<Time>> map =new HashMap<String,List<Time>>();
         List<Time> list = new ArrayList<Time>();
    }

    public void up(View v){
        if(mark!=0){
            mark--;
            reset();
        }else {
            Uihelper.showToast(this,"已经在第一页了");
        }

    }

    public void down(View v){
        if(mark<group&&group>1){
            mark++;
            reset();



        }else {
            Uihelper.showToast(this,"只有一页");
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.rl_1_left:
                break;
            case R.id.rl_2_left:
                break;
            case R.id.rl_3_left:
                break;
            case R.id.rl_4_left:
                break;
            case R.id.rl_5_left:
                break;
            case R.id.rl_6_left:
                break;
            case R.id.rl_7_left:
                break;
            case R.id.rl_1_right:
                break;
            case R.id.rl_2_right:
                break;
            case R.id.rl_3_right:
                break;
            case R.id.rl_4_right:
                break;
            case R.id.rl_5_right:
                break;
            case R.id.rl_6_right:
                break;
            case R.id.rl_7_right:
                break;
        }
    }

    private void reset(){
        tv_time1.setText("");
        tv_time2.setText("");
        tv_time3.setText("");
        tv_time4.setText("");
        tv_time5.setText("");
        tv_time6.setText("");
        tv_time7.setText("");
        img_am1.setVisibility(View.INVISIBLE);
        img_am2.setVisibility(View.INVISIBLE);
        img_am3.setVisibility(View.INVISIBLE);
        img_am4.setVisibility(View.INVISIBLE);
        img_am5.setVisibility(View.INVISIBLE);
        img_am6.setVisibility(View.INVISIBLE);
        img_am7.setVisibility(View.INVISIBLE);
        img_pm1.setVisibility(View.INVISIBLE);
        img_pm2.setVisibility(View.INVISIBLE);
        img_pm3.setVisibility(View.INVISIBLE);
        img_pm4.setVisibility(View.INVISIBLE);
        img_pm5.setVisibility(View.INVISIBLE);
        img_pm6.setVisibility(View.INVISIBLE);
        img_pm7.setVisibility(View.INVISIBLE);
    }


    public void lessThan7(){
        SimpleDateFormat sDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String    date    =    sDateFormat.format(new java.util.Date());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            d1=sdf.parse(date);
                if(remainder==1){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                }
                if(remainder==2){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                }
                if(remainder==3){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                }
                if(remainder==4){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                }
                if(remainder==5){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                    tv_time5.setText(TimeTools.getDayTime(d1,5));
                }
                if(remainder==6){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                    tv_time5.setText(TimeTools.getDayTime(d1,5));
                    tv_time6.setText(TimeTools.getDayTime(d1,6));
                }
                if(remainder==7){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                    tv_time5.setText(TimeTools.getDayTime(d1,5));
                    tv_time6.setText(TimeTools.getDayTime(d1,6));
                    tv_time7.setText(TimeTools.getDayTime(d1,7));
                }


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
