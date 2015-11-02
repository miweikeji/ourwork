package app.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.miweikeij.app.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.adapter.GroupMemberAdapter;
import app.entity.GroupMembe;
import app.entity.GroupMemberResult;
import app.entity.Meta;
import app.entity.Phone;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.MobileOS;
import app.utils.Uihelper;
import app.views.NavigationBar;

public class AddMembersActivity extends BaseActivity implements View.OnClickListener {


    private LinearLayout line_add_memebers;
    private  EditText et_members_phone;
    private int count;
    private HashMap<Integer,String> phoneMap = new HashMap<Integer,String>();
    private ArrayList<String> listPhone=new ArrayList<String>();
    private HashMap<Integer,View> viewMap = new HashMap<Integer,View>();
    private  String groupId;
    @Override
    public void obtainData() {
        groupId =  getIntent().getStringExtra("groupId");
    }

    @Override
    public void initUI() {


        Map<String,List<Meta>> map =new HashMap<String,List<Meta>>();
        Gson gson = new Gson();
        Meta meta = new Meta();
        Meta meta1 = new Meta();
        meta.setMsg("nihao");
        meta1.setMsg("淡淡的忧伤");
        List<Meta> list = new ArrayList<Meta>();
        list.add(meta);
        list.add(meta1);
        map.put("phones", list);
        String json = gson.toJson(map);
        Toast.makeText(this,json,1).show();
        et_members_phone = (EditText) findViewById(R.id.et_members_phone);
        line_add_memebers = (LinearLayout) findViewById(R.id.line_add_memebers);
        RelativeLayout rl_add_member = (RelativeLayout) findViewById(R.id.rl_add_member);
        rl_add_member.setOnClickListener(this);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_add_members;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("添加班组成员");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_add_member:
                String phone = et_members_phone.getText().toString();
                if(MobileOS.isMobileNO(phone)){
                    if(!listPhone.contains(phone)){
                        addMembers(phone);
                    }else {
                        Uihelper.showToast(this,"您已添加写过了");
                    }
                }else {
                    Uihelper.showToast(this,"手机号码不正确");
                }
                break;
        }
    }

    private void addMembers(String phone) {
        listPhone.add(phone);
        et_members_phone.setText("");
        count++;
        phoneMap.clear();
        line_add_memebers.removeAllViews();
        for (int i=0;i<count;i++){
            View layout = getLayoutInflater().inflate(R.layout.item_add_members, null);
            RelativeLayout rl_deleate = (RelativeLayout) layout.findViewById(R.id.rl_deleate);
            TextView tv_memeber_phone = (TextView) layout.findViewById(R.id.tv_memeber_phone);
            tv_memeber_phone.setText(listPhone.get(i));
            viewMap.put(i, layout);
            rl_deleate.setId(i);
            phoneMap.put(i, listPhone.get(i));
            rl_deleate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count--;
                    int id = v.getId();
                    String phone = phoneMap.get(id);
                    line_add_memebers.removeView(viewMap.get(id));

                    for (int i = 0; i < listPhone.size(); i++) {
                        if (phone.equals(listPhone.get(i))) {
                            listPhone.remove(i);
                        }
                    }

                }
            });
            line_add_memebers.addView(layout);
        }
    }

    public void addComplete(View view){
        if(listPhone.size()!=0){
            Map<String,List<Phone>> map =new HashMap<String,List<Phone>>();
            List<Phone> list = new ArrayList<Phone>();
            for (int i=0;i<listPhone.size();i++){
                Phone  phones = new Phone();
                phones.setPhone(listPhone.get(i));
                list.add(phones);
            }
            map.put("phones",list);
            Gson gson = new Gson();
            String json = gson.toJson(map);
            netWorkData(json);
        }else {
            Uihelper.showToast(AddMembersActivity.this, "请添加班组成员");
        }

    }

    private void netWorkData(String json) {
        showWaitingDialog();
        HttpRequest.addGroupCraf(this, "", json, new ICallback<Meta>() {
            @Override
            public void onSucceed(Meta result) {
                disMissWaitingDialog();
            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(AddMembersActivity.this, error);
                disMissWaitingDialog();
            }
        });
    }
}
