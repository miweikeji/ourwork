package app.net;

import android.content.Context;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import app.entity.CaseResult;
import app.entity.Crafts;
import app.entity.CraftsResult;
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

    /**
     * 测试
     */
    public static void testHttp(Context context, final ICallback<Meta> callback, String mobile,
                                String type) {
        ArrayList<Param> mList = new ArrayList<Param>();
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
        ArrayList<Param> mList = new ArrayList<Param>();
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
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("mobile", mobile));
        mList.add(new Param("password",password));
        MyLog.e("","请求参数=="+mList.toString());
        new MyAsyncTask(context, Urls.BasicInfo, mList, new ICallback<String>() {

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

    /**
     * 基础信息
     */
    public static void infoHttp(Context context, String id,String name,String age,
                                int wroktype,String workage,String workhome,final ICallback<Meta> callback
    ) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("id", id));
        mList.add(new Param("name", name));
        mList.add(new Param("age", age));
        mList.add(new Param("wroktype", "" + wroktype));
        mList.add(new Param("workage", workage));
        mList.add(new Param("workhome",workhome));
        MyLog.e("","请求参数=="+mList.toString());
        new MyAsyncTask(context, Urls.BasicInfo, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {

                Meta mate = JsonUtil.parseObject(result, Meta.class);
                if (mate.getStatus() == 0) {
                    callback.onSucceed(mate);
                } else {
                    callback.onFail(mate.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }

    /**
     * 工匠信息
     */
    public static void craftsmanInfoHttp(Context context, String cid,final ICallback<CraftsResult> callback
    ) {

        ArrayList<Param> mList = new ArrayList<Param>();


        mList.add(new Param("cid", cid));

        MyLog.e("","请求参数=="+mList.toString());
        new MyAsyncTask(context, Urls.craftsman_info, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("" ,"result=="+result.toString());
                CraftsResult mate = JsonUtil.parseObject(result, CraftsResult.class);

                if (mate.getStatus() == 0) {
                    callback.onSucceed(mate);
                } else {
                    callback.onFail(mate.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }

    /**
     * 获取工匠参与的所有案例
     */
    public static void allCaseHttp(Context context, String cid,int p,final ICallback<CaseResult> callback
    ) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", cid));
        mList.add(new Param("p", ""+p));

        MyLog.e("", "请求参数==" + mList.toString());
        new MyAsyncTask(context, Urls.all_case, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "result==" + result.toString());
                CaseResult mate = JsonUtil.parseObject(result, CaseResult.class);

                if (mate.getStatus() == 0) {
                    callback.onSucceed(mate);
                } else {
                    callback.onFail(mate.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }
}
