package app.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.miweikeij.app.R;

import app.views.NavigationBar;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        TextView forgetPsw = (TextView)findViewById(R.id.forget_psw);
        TextView register = (TextView)findViewById(R.id.toRegister);
        forgetPsw.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        forgetPsw.getPaint().setAntiAlias(true);//抗锯齿
        register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        register.getPaint().setAntiAlias(true);//抗锯齿
        register.setOnClickListener(this);
        forgetPsw.setOnClickListener(this);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_login;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setBarInVisibile();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toRegister:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.forget_psw:

                break;
        }
    }
}
