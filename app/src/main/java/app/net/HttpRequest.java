package app.net;

import android.content.Context;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import app.entity.AddMemberError;
import app.entity.AdvertiseResult;
import app.entity.ArrangeTaskResult;
import app.entity.CaseResult;
import app.entity.ConstructPlanResult;
import app.entity.CraGroupResult;
import app.entity.Crafts;
import app.entity.CraftsResult;
import app.entity.GroupGangerResult;
import app.entity.GroupMemberResult;
import app.entity.HousesByLyfResult;
import app.entity.Meta;
import app.entity.MyFriendsResult;
import app.entity.RegisterInfo;
import app.entity.SearchResult;
import app.entity.UserInfo;
import app.entity.UserInfoResult;
import app.entity.craftsList;
import app.entity.craftsListResult;
import app.tools.MyLog;
import app.utils.JsonUtil;
import app.utils.MobileOS;
import app.utils.UserUtil;

/**
 * Created by Jackie on 2015/9/4.
 */
public class HttpRequest {

    /**
     * 发送验证码
     */
    public static void sendCaptcha(Context context, final ICallback<Meta> callback, String mobile,
                                String type) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("mobile", mobile));
        mList.add(new Param("type", type));

        new MyAsyncTask(context, Urls.getMsgCode, mList, new ICallback<String>() {

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
    public static void loginHttp(Context context, String mobile,String password, final ICallback<UserInfoResult> callback
                                    ) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("mobile", mobile));
        mList.add(new Param("password",password));
        MyLog.e("","请求参数=="+mList.toString());
        new MyAsyncTask(context, Urls.login, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {

                UserInfoResult userInfoResult = JsonUtil.parseObject(result, UserInfoResult.class);
                if (userInfoResult.getStatus() == 0) {
                    callback.onSucceed(userInfoResult);
                } else {
                    callback.onFail(userInfoResult.getMsg());
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
                                String wroktype,String workage,String workhome,final ICallback<Meta> callback
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
    public static void craftsmanGroupHttp(Context context, String craftsId,final ICallback<CraGroupResult> callback
    ) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("craftsId", craftsId));
        MyLog.e("", "请求参数==" + mList.toString());
        new MyAsyncTask(context, Urls.getCraGroup, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "result==" + result.toString());
                CraGroupResult mate = JsonUtil.parseObject(result, CraGroupResult.class);

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

    /**
     * 班组成员接口
     */
    public static void getGroupCraftsHttp(Context context, String gangerId,String groupId,int p,final ICallback<GroupMemberResult> callback
    ) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("gangerId", gangerId));
        mList.add(new Param("groupId", groupId));
        mList.add(new Param("p", ""+p));

        MyLog.e("", "请求参数==" + mList.toString());
        new MyAsyncTask(context, Urls.getGroupCrafts, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "result==" + result.toString());
                GroupMemberResult mate = JsonUtil.parseObject(result, GroupMemberResult.class);

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
     * 班组成员接口
     */
    public static void getGroupGangerHttp(Context context, String gangerId,final ICallback<GroupGangerResult> callback
    ) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("gangerId", gangerId));

        MyLog.e("", "请求参数==" + mList.toString());
        new MyAsyncTask(context, Urls.getGroupGanger, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "result==" + result.toString());
                GroupGangerResult mate = JsonUtil.parseObject(result, GroupGangerResult.class);

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
     * 找回密码
     */
    public static void findPsw(Context context, final ICallback<Meta> callback, String mobile,String password,
                                    String code) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("mobile", mobile));
        mList.add(new Param("code", code));
        mList.add(new Param("password", password));
        mList.add(new Param("deviceid", MobileOS.getDeviceId(context)));
        MyLog.e("", "请求参数==" + mList.toString());
        new MyAsyncTask(context, Urls.forgetPassword, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {

                Meta meta = JsonUtil.parseObject(result, Meta.class);
                if (meta.getStatus() == 0) {
                    callback.onSucceed(meta);
                } else {
                    callback.onFail(meta.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }

    /**
     * 预约中列表接口
     */
    public static void getHousesByLyf(Context context,String craftsId ,int p,final ICallback<HousesByLyfResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("craftsId", craftsId));
        mList.add(new Param("p", ""+p));

        MyLog.e("", "请求参数==" + mList.toString());
        new MyAsyncTask(context, Urls.getHousesByLyf, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {

                HousesByLyfResult meta = JsonUtil.parseObject(result, HousesByLyfResult.class);
                if (meta.getStatus() == 0) {
                    callback.onSucceed(meta);
                } else {
                    callback.onFail(meta.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }

    /**
     * 确定预约列表接口
     */
    public static void getHousesByAppointmentLyf(Context context,String craftsId ,int p,final ICallback<HousesByLyfResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("craftsId", craftsId));
        mList.add(new Param("p", ""+p));

        MyLog.e("", "请求参数==" + mList.toString());
        new MyAsyncTask(context, Urls.getHousesByAppointmentLyf, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {

                HousesByLyfResult meta = JsonUtil.parseObject(result, HousesByLyfResult.class);
                if (meta.getStatus() == 0) {
                    callback.onSucceed(meta);
                } else {
                    callback.onFail(meta.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }


    /**
     * 预约历史列表接口
     */
    public static void getHousesByHistoryLyf(Context context,String craftsId ,int p,final ICallback<HousesByLyfResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("craftsId", craftsId));
        mList.add(new Param("p", ""+p));

        MyLog.e("", "请求参数==" + mList.toString());
        new MyAsyncTask(context, Urls.getHousesByHistoryLyf, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {

                HousesByLyfResult meta = JsonUtil.parseObject(result, HousesByLyfResult.class);
                if (meta.getStatus() == 0) {
                    callback.onSucceed(meta);
                } else {
                    callback.onFail(meta.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }

    /**
     * 婉拒预约接口
     */
    public static void refuseAppointmentLyf(Context context,String houseId ,String ownerId,String craftsId,
                                            String craftsName,String yyTime,final ICallback<Meta> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("houseId", houseId));
        mList.add(new Param("ownerId", ownerId));
        mList.add(new Param("craftsId", craftsId));
        mList.add(new Param("craftsName", craftsName));
        mList.add(new Param("yyTime", yyTime));

        MyLog.e("", "请求参数==" + mList.toString());
        new MyAsyncTask(context, Urls.refuseAppointmentLyf, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {

                Meta meta = JsonUtil.parseObject(result, Meta.class);
                if (meta.getStatus() == 0) {
                    callback.onSucceed(meta);
                } else {
                    callback.onFail(meta.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }
    /**
     * 获取接受预约日志接口
     */
    public static void acceptAppointmentLyf(Context context,String houseId ,String ownerId,String craftsId,
                                            String craftsName,String yyTime,final ICallback<Meta> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("houseId", houseId));
        mList.add(new Param("ownerId", ownerId));
        mList.add(new Param("craftsId", craftsId));
        mList.add(new Param("craftsName", craftsName));
        mList.add(new Param("yyTime", yyTime));

        MyLog.e("", "请求参数==" + mList.toString());
        new MyAsyncTask(context, Urls.acceptAppointmentLyf, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {

                Meta meta = JsonUtil.parseObject(result, Meta.class);
                if (meta.getStatus() == 0) {
                    callback.onSucceed(meta);
                } else {
                    callback.onFail(meta.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }

    /**
     * 所有工友
     */
    public static void getAllcrafts(Context context,String jiang ,int p,final ICallback<craftsListResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("jiang", jiang));
        mList.add(new Param("p", "" + p));


        new MyAsyncTask(context, Urls.getAllcrafts, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "请求参数==" + result.toString());
                craftsListResult meta = JsonUtil.parseObject(result, craftsListResult.class);
                if (meta.getStatus() == 0) {
                    callback.onSucceed(meta);
                } else {
                    callback.onFail(meta.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }


    /**
     * 好友接口
     */
    public static void getMyfriend (Context context,String cid ,int p,final ICallback<MyFriendsResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", cid));
        mList.add(new Param("p", "" + p));


        new MyAsyncTask(context, Urls.getMyfriend, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "请求参数==" + result.toString());
                MyFriendsResult meta = JsonUtil.parseObject(result, MyFriendsResult.class);
                if (meta.getStatus() == 0) {
                    callback.onSucceed(meta);
                } else {
                    callback.onFail(meta.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }


    /**
     * 未安排任务接口
     */
    public static void unArrangeTask (Context context,String jiang ,int p,final ICallback<ArrangeTaskResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("jiang", jiang));
        mList.add(new Param("p", "" + p));


        new MyAsyncTask(context, Urls.unArrangeTask, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "请求参数==" + result.toString());
                ArrangeTaskResult meta = JsonUtil.parseObject(result, ArrangeTaskResult.class);
                if (meta.getStatus() == 0) {
                    callback.onSucceed(meta);
                } else {
                    callback.onFail(meta.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }
    /**
     * 广告接口
     */
    public static void advertise (Context context,final ICallback<AdvertiseResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();


        new MyAsyncTask(context, Urls.advertise, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                AdvertiseResult advertiseResult = JsonUtil.parseObject(result, AdvertiseResult.class);
                if (advertiseResult.getStatus() == 0) {
                    callback.onSucceed(advertiseResult);
                } else {
                    callback.onFail(advertiseResult.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }
    /**
     * 工头认证
     */
    public static void workHeadAudit (Context context,final ICallback<Meta> callback,String realname,String cworknum,String groupInfo,String cardImg,String cradBImg ) {
        ArrayList<Param> mList = new ArrayList<Param>();

        mList.add(new Param("craftsId", UserUtil.getUserId(context)));
        mList.add(new Param("realname", realname));
        mList.add(new Param("cworknum", cworknum));
        mList.add(new Param("groupInfo", groupInfo));
        mList.add(new Param("cardImg", cardImg));
        mList.add(new Param("cradBImg", cradBImg));


        new MyAsyncTask(context, Urls.workHeadAudit, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                Meta meta = JsonUtil.parseObject(result, Meta.class);
                if (meta.getStatus() == 0) {
                    callback.onSucceed(meta);
                } else {
                    callback.onFail(meta.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }
    /**
     * 添加班组成员
     */
    public static void addGroupCrafts (Context context,final ICallback<Meta> callback,String groupId,String phones) {
        ArrayList<Param> mList = new ArrayList<Param>();

        mList.add(new Param("phones", phones));
        mList.add(new Param("groupId", groupId));


        new MyAsyncTask(context, Urls.addGroupCrafts, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                Meta meta = JsonUtil.parseObject(result, Meta.class);
                if (meta.getStatus() == 0) {
                    callback.onSucceed(meta);
                } else if (meta.getStatus() == 1){
                    AddMemberError addMemberError = JsonUtil.parseObject(result, AddMemberError.class);
                    callback.onSucceed(addMemberError);
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }


    /**
     *
     根据工种类型查询工匠列表

     */
    public static void searchcraftsByType(Context context,String content ,int p,final ICallback<SearchResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("content", content));
        mList.add(new Param("p", "" + p));


        new MyAsyncTask(context, Urls.searchcraftsByType, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "请求参数==" + result.toString());
                SearchResult meta = JsonUtil.parseObject(result, SearchResult.class);
                if (meta.getStatus() == 0) {
                    callback.onSucceed(meta);
                } else {
                    callback.onFail(meta.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }

    /**
     *
     工作安排施工中

     */
    public static void constructPlan(Context context,String cid ,int p,final ICallback<ConstructPlanResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", cid));
        mList.add(new Param("p", "" + p));


        new MyAsyncTask(context, Urls.constructPlan, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "请求参数==" + result.toString());
                ConstructPlanResult meta = JsonUtil.parseObject(result, ConstructPlanResult.class);
                if (meta.getStatus() == 0) {
                    callback.onSucceed(meta);
                } else {
                    callback.onFail(meta.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }
}