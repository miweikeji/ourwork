package app.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.miweikeij.app.R;

import app.entity.JsonData;
import app.entity.UserInfo;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/25.
 */
public class NewDecorationActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    private RadioButton rb_lady,
            rb_sir,rb_type_1,rb_type_2,rb_type_3,rb_type_4,
            rb_type_5,rb_type_6,rb_type_one,rb_type_two,rb_type_three;
    private EditText tv_sir_and_lady;
    private EditText et_village,et_area;
    private String house_type;
    private String house_craftmode;
    private String owner_sex;
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

        et_village = (EditText) findViewById(R.id.et_village);
        et_area = (EditText) findViewById(R.id.et_area);
        tv_sir_and_lady = (EditText) findViewById(R.id.tv_sir_and_lady);

        rb_lady = (RadioButton) findViewById(R.id.rb_lady);
        rb_sir = (RadioButton) findViewById(R.id.rb_sir);
        rb_type_1 = (RadioButton) findViewById(R.id.rb_type_1);
        rb_type_2 = (RadioButton) findViewById(R.id.rb_type_2);
        rb_type_3 = (RadioButton) findViewById(R.id.rb_type_3);
        rb_type_4 = (RadioButton) findViewById(R.id.rb_type_4);
        rb_type_5 = (RadioButton) findViewById(R.id.rb_type_5);
        rb_type_6 = (RadioButton) findViewById(R.id.rb_type_6);
        rb_type_one = (RadioButton) findViewById(R.id.rb_type_one);
        rb_type_two = (RadioButton) findViewById(R.id.rb_type_two);
        rb_type_three = (RadioButton) findViewById(R.id.rb_type_three);
        setOnChceked();
    }

    private void setOnChceked() {
        rb_lady.setOnCheckedChangeListener(this);
        rb_sir.setOnCheckedChangeListener(this);
        rb_type_1.setOnCheckedChangeListener(this);
        rb_type_2.setOnCheckedChangeListener(this);
        rb_type_3.setOnCheckedChangeListener(this);
        rb_type_4.setOnCheckedChangeListener(this);
        rb_type_5.setOnCheckedChangeListener(this);
        rb_type_6.setOnCheckedChangeListener(this);
        rb_type_one.setOnCheckedChangeListener(this);
        rb_type_two.setOnCheckedChangeListener(this);
        rb_type_three.setOnCheckedChangeListener(this);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_new_decoration;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("新建装修计划");

    }

    public void newDecoration(View v){
       if( rb_type_1.isChecked()){
           house_type = "一居";
       }else if(rb_type_2.isChecked()){
           house_type = "二居";
       }else if(rb_type_3.isChecked()){
           house_type = "三居";
       }else if(rb_type_4.isChecked()){
           house_type = "四居";
       }else if(rb_type_5.isChecked()){
           house_type = "别墅";
       }else if(rb_type_6.isChecked()){
           house_type = "其他";
       }

        if(rb_type_one.isChecked()){
            house_craftmode = "半包";
        }else if(rb_type_two.isChecked()){
            house_craftmode = "全包";
        }else if(rb_type_two.isChecked()){
            house_craftmode = "清包";
        }
        String house_xiaoqu = et_village.getText().toString().trim();
        String house_area = et_area.getText().toString().trim();
        String owner_name = tv_sir_and_lady.getText().toString().trim();

        if(rb_sir.isChecked()){
            owner_sex="1";
        }else if(rb_lady.isChecked()){
            owner_sex="2";
        }

        JsonData jsonData = new JsonData();
        jsonData.setHouse_area(house_area);
        jsonData.setHouse_craftmode(house_craftmode);
        jsonData.setHouse_type(house_type);
        jsonData.setHouse_xiaoqu(house_xiaoqu);
        jsonData.setOwner_sex(owner_sex);
        jsonData.setOwner_name(owner_name);
        jsonData.setType("1");
        jsonData.setServertype("0");
        jsonData.setWho("0");
        jsonData.setWhoid(UserInfo.getInstance().getId());
        Intent intent = new Intent(this, ConstructionTasksActivity.class);
        startActivity(intent);
        if(house_xiaoqu!=null){
            if(house_area!=null){
                if(owner_sex!=null){
                    if(house_type!=null){
                        if(house_craftmode!=null){
                            if(owner_name!=null){
//                                Bundle bundle = new Bundle();
//                                bundle.putSerializable("NewDecorationActivity", jsonData);
//                                Intent intent = new Intent(this, ConstructionTasksActivity.class);
//                                intent.putExtras(bundle);
//                                startActivity(intent);
                            }else {
                                Uihelper.showToast(this,"请填写业主的名字");
                            }
                        }else {
                            Uihelper.showToast(this,"请选择房屋施工方式");
                        }
                    }else {
                        Uihelper.showToast(this,"请选择房屋的户型");
                    }
                }else {
                    Uihelper.showToast(this,"请选择性别");
                }
            }else {
                Uihelper.showToast(this,"请填写房屋面积");
            }
        }else {
            Uihelper.showToast(this,"请填写小区");
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.rb_sir:
                if(isChecked){
//                    tv_sir_and_lady.setText("先生");
                }
                break;
            case R.id.rb_lady:
                if(isChecked){
//                    tv_sir_and_lady.setText("女士");
                }
                break;

        }
    }
}
