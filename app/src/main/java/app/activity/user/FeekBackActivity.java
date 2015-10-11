package app.activity.user;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.miweikeij.app.R;

import app.activity.BaseActivity;
import app.entity.Meta;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/11.
 */
public class FeekBackActivity extends BaseActivity{
    private EditText ed_feedback;

    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        ed_feedback = (EditText) findViewById(R.id.ed_feedback);
        ed_feedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence string, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("意见反馈");

    }

    public void btn_click(View v) {

        String string_feekback = ed_feedback.getText().toString().trim();

        if (!TextUtils.isEmpty(string_feekback)) {
//
//            HttpRequest.feekback(mActivity, new ICallback<Meta>() {
//
//                @Override
//                public void onSucceed(Meta result) {
//                    dialog.dismiss();
//                    finish();
//                    Uihelper.showToast(mActivity, "提交成功");
//
//                }
//
//                @Override
//                public void onFail(String error) {
//                    dialog.dismis();
//                    Uihelper.showToast(mActivity, error);
//                }
//            }, Encrypt.getGBK(string_feekback));
//
//        } else {
//            Uihelper.showToast(mActivity, "请写下您的意见或建议");
//        }
        }
    }
}
