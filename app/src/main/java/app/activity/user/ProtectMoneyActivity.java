package app.activity.user;

import android.content.Intent;
import android.view.View;

import com.miweikeij.app.R;

import app.activity.BaseActivity;
import app.dialog.DialogCharge;
import app.dialog.DialogRefund;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/11.
 */
public class ProtectMoneyActivity extends BaseActivity {
    private DialogCharge dialogCharge;
    private DialogRefund dialogRefund;

    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {


    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_protectmoney;
    }

    //退款
    public void  btn_refund(View v){

        if (dialogRefund==null){
            dialogRefund=new DialogRefund(mActivity) {
                @Override
                public void positionBtnClick(String s) {

                }
            };
        }
        dialogRefund.show();

    }
    //充值
    public void  btn_charge(View v){


        if (dialogCharge==null){
            dialogCharge=new DialogCharge(mActivity) {
                @Override
                public void positionBtnClick(String s) {

                }
            };
        }
       dialogCharge.show();

    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("保证金");
        mBar.setRightTitle("记录");
        mBar.setRightOnClick(new NavigationBar.RightOnClick() {
            @Override
            public void setRightOnClick() {
                startActivity(new Intent(mActivity,ProtectRecordActivity.class));
            }
        });

    }
}
