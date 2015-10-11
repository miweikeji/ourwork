package app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.miweikeij.app.R;

import app.views.NavigationBar;

public class CraftsmanGroupActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

        RelativeLayout rl_owner_reservation = (RelativeLayout) findViewById(R.id.rl_owner_reservation);
        rl_owner_reservation.setOnClickListener(this);
        RelativeLayout rl_group_members = (RelativeLayout) findViewById(R.id.rl_group_members);
        rl_group_members.setOnClickListener(this);
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
