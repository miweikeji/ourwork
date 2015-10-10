package app.net;

import android.content.Context;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import app.entity.Meta;
import app.entity.RegisterInfo;
import app.entity.UserInfo;
import app.tools.MyLog;
import app.utils.JsonUtil;
import app.utils.MobileOS;

/**
 * Created by Jackie on 2015/9/4.
 */
public class HttpRequest {

    private static List<Param> mList;

    /**
     * 测试
     */
    public static void testHttp(Context context, final ICallback<Meta> callback, String mobile,
                                String type) {
        if (mList == null) {
            mList = new ArrayList<Param>();
        }
        mList.clear();
        mList.add(new Param("mobile", mobile));
        mList.add(new Param("type", type));

        new MyAsyncTask(context, Urls.test, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {

                Meta test = JsonUtil.parseObject(result, Meta.class);
                if (test.getStatus() == 0) {
                    callback.onSucceed(test);
                } else {
                    callback.onFail(test.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }


    /**
     * 注册
     */
    public static void registerHttp(Context context, final ICallback<RegisterInfo> callback, String mobile,String password,
                                String code) {
        if (mList == null) {
            mList = new ArrayList<Param>();
        }
        mList.clear();
        mList.add(new Param("mobile", mobile));
        mList.add(new Param("code", code));
        mList.add(new Param("password", password));
        mList.add(new Param("deviceid", MobileOS.getDeviceId(context)));
        MyLog.e("", "请求参数==" + mList.toString());
        new MyAsyncTask(context, Urls.register, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {

                RegisterInfo registerInfo = JsonUtil.parseObject(result, RegisterInfo.class);
                if (registerInfo.getStatus() == 0) {
                    callback.onSucceed(registerInfo);
                } else {
                    callback.onFail(registerInfo.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }

    /**
     * 登录
     */
    public static void loginHttp(Context context, String mobile,String password, final ICallback<UserInfo> callback
                                    ) {
        if (mList == null) {
            mList = new ArrayList<Param>();
        }
        mList.clear();
        mList.add(new Param("mobile", mobile));
        mList.add(new Param("password",password));
        MyLog.e("","请求参数=="+mList.toString());
        new MyAsyncTask(context, Urls.login, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {

                UserInfo userInfo = JsonUtil.parseObject(result, UserInfo.class);
                if (userInfo.getStatus() == 0) {
                    callback.onSucceed(userInfo);
                } else {
                    callback.onFail(userInfo.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }
}
