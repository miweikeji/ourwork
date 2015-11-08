package app.activity.mywork;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.miweikeij.app.R;

import app.activity.BaseActivity;
import app.entity.Meta;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * 评价工匠
 * Created by tlt on 2015/10/26.
 */
public class ValueCraftActivity extends BaseActivity {
    private TextView tvAccount;
    private EditText et_valuecraft;
    private TextView tv_craft_name;
    private String craftId;
    private RatingBar ratingBar_quality;
    private RatingBar ratingBarValue;

    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        ratingBarValue = (RatingBar) findViewById(R.id.ratingBar_value);
        ratingBar_quality = (RatingBar) findViewById(R.id.ratingBar_quality);
        tvAccount = (TextView) findViewById(R.id.tv_account);
        tv_craft_name = (TextView) findViewById(R.id.tv_craft_name);
        et_valuecraft = (EditText) findViewById(R.id.et_valuecraft);
        findViewById(R.id.frame_selectcraft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, CraftsByHouseIdActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        et_valuecraft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence string, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String text = s.toString();
                if (text.length() > 140) {
                    Uihelper.showToast(mActivity, "最多输入140个字");
                } else {
                    tvAccount.setText(text.length() + "");
                }

            }
        });

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_valuecraft;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {

            craftId = data.getStringExtra("craftId");
            String craftName = data.getStringExtra("craftName");
            if (!TextUtils.isEmpty(craftName)) {
                tv_craft_name.setText(craftName);
            }
        }
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工匠评价");
    }

    public void btn_value(View v) {
//        if (TextUtils.isEmpty(craftId)) {
//            Uihelper.showToast(mActivity, "未选择工匠");
//            return;
//        }
        String advice = et_valuecraft.getText().toString();
        if (TextUtils.isEmpty(advice)) {
            Uihelper.showToast(mActivity, "评价不能为空");
            return;
        }
        int ratingValue = (int) ratingBarValue.getRating();
        int ratingQuality = (int) ratingBar_quality.getRating();
        showWaitingDialog();
        HttpRequest.addComment(mActivity, "100", "147", "753665", ratingValue, ratingQuality, advice, new ICallback<Meta>() {
            @Override
            public void onSucceed(Meta result) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity, "提交成功");
                finish();
            }

            @Override
            public void onFail(String error) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity, error);
            }
        });


    }
}
