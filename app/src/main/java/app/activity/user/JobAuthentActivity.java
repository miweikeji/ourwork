package app.activity.user;

import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.miweikeij.app.R;

import app.activity.AddMembersActivity;
import app.activity.BaseActivity;
import app.activity.GroupMembersActivity;
import app.views.NavigationBar;

/**
 * 工头认证
 * Created by tlt on 2015/10/12.
 */

public class JobAuthentActivity extends BaseActivity implements View.OnClickListener {
    private EditText edit_name;
    private EditText edit_groupname;
    private EditText edit_style;
    private EditText edit_intraduce;
    private EditText edit_authent;
    private TextView tv_addMember;

    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
         edit_name=(EditText)findViewById(R.id.edit_name);
        edit_authent=(EditText)findViewById(R.id.edit_authent);
        edit_groupname=(EditText)findViewById(R.id.edit_groupname);
        edit_style=(EditText)findViewById(R.id.edit_style);
        edit_intraduce=(EditText)findViewById(R.id.edit_intraduce);

        tv_addMember=(TextView)findViewById(R.id.tv_addMember);
        tv_addMember.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        tv_addMember.setOnClickListener(this);

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_jobauthent;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工头认证");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_addMember:

                startActivity(new Intent(mActivity,AddMembersActivity.class));

                break;
            default:
                break;
        }
    }
}
