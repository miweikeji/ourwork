package app.net;

import android.content.Context;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.entity.AddMemberError;
import app.entity.AdvertiseResult;
import app.entity.ArrangeTaskResult;
import app.entity.BuiltTask;
import app.entity.BuiltTaskResult;
import app.entity.CaseResult;
import app.entity.ConstructPlanResult;
import app.entity.CraGroupResult;
import app.entity.Crafts;
import app.entity.CraftsResult;
import app.entity.DetailPlanResult;
import app.entity.GroupGangerResult;
import app.entity.GroupMemberResult;
import app.entity.HouseInfoResult;
import app.entity.HousesByLyfResult;
import app.entity.Meta;
import app.entity.MyFriendsResult;
import app.entity.MyScore;
import app.entity.MyWorkDetailMessage;
import app.entity.MyWorkDetailResult;
import app.entity.MyWorksListResult;
import app.entity.ProtectRecordResult;
import app.entity.RegisterInfo;
import app.entity.ScoreResult;
import app.entity.SearchResult;
import app.entity.SingInResult;
import app.entity.UserInfo;
import app.entity.UserInfoResult;
import app.entity.WorkDetailResult;
import app.entity.WorkListResult;
import app.entity.craftsList;
import app.entity.craftsListResult;
import app.tools.MyLog;
import app.tools.StatusTools;
import app.tools.StringConverter;
import app.tools.UserInfoJsonTools;
import app.utils.Constants;
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
                MyLog.e("","请求参数=="+result.toString());
                UserInfoResult userInfoResult = JsonUtil.parseObject(result, UserInfoResult.class);
                if (userInfoResult.getStatus() == 0) {
                    UserInfoJsonTools.jsonUserInfo(result);
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
    /**
     *
     工作安排完成

     */
    public static void finishPlan(Context context,String cid ,int p,final ICallback<ConstructPlanResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", cid));
        mList.add(new Param("p", "" + p));


        new MyAsyncTask(context, Urls.finishPlan, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "请求参数==" + result.toString());
                //{"houseList":null,"page":0,"status":0,"msg":"","sessionid":"40a5be61562f73454e372"}
                try {
                    JSONObject object = new JSONObject(result);
                    int status = object.getInt("status");
                    if(status==0){
                        int page = object.getInt("page");
                        if(page==0){
                            callback.onFail(Constants.JSON_HAS_NULL);
                        }else {
                            ConstructPlanResult meta = JsonUtil.parseObject(result, ConstructPlanResult.class);
                                callback.onSucceed(meta);

                        }
                    }else {
                        callback.onFail(object.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
     工作安排详情接口

     */
    public static void detailPlan(Context context,String cid ,String houseId,int p,final ICallback<DetailPlanResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", cid));
        mList.add(new Param("houseId", houseId));
        mList.add(new Param("p", "" + p));


        new MyAsyncTask(context, Urls.detailPlan, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "请求参数==" + result.toString());
                //{"houseList":null,"page":0,"status":0,"msg":"","sessionid":"40a5be61562f73454e372"}
                try {
                    JSONObject object = new JSONObject(result);
                    int status = object.getInt("status");
                    if(status==0){
                        int page = object.getInt("page");
                        if(page==0){
                            callback.onFail(Constants.JSON_HAS_NULL);
                        }else {
//                            GsonBuilder gb = new GsonBuilder();
//                            gb.registerTypeAdapter(String.class, new StringConverter());
//                            Gson gson = gb.create();
                            Gson gson = new Gson();
                            DetailPlanResult meta= gson.fromJson(result, DetailPlanResult.class);
//                            DetailPlanResult meta = JsonUtil.parseObject(result, DetailPlanResult.class);
                            callback.onSucceed(meta);

                        }
                    }else {
                        callback.onFail(object.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
     获取已建计划接口

     */
    public static void getTask(Context context,String cid ,int p,final ICallback<BuiltTaskResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", cid));
        mList.add(new Param("p", ""+p));


        new MyAsyncTask(context, Urls.getTask, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "请求参数==" + result.toString());
                //{"houseList":null,"page":0,"status":0,"msg":"","sessionid":"40a5be61562f73454e372"}
                try {
                    JSONObject object = new JSONObject(result);
                    int status = object.getInt("status");
                    if(status==0){
                        int page = object.getInt("page");
                        if(page==0){
                            callback.onFail(Constants.JSON_HAS_NULL);
                        }else {
//                            GsonBuilder gb = new GsonBuilder();
//                            gb.registerTypeAdapter(String.class, new StringConverter());
//                            Gson gson = gb.create();
                            Gson gson = new Gson();
                            BuiltTaskResult meta= gson.fromJson(result, BuiltTaskResult.class);
//                            DetailPlanResult meta = JsonUtil.parseObject(result, DetailPlanResult.class);
                            callback.onSucceed(meta);

                        }
                    }else {
                        callback.onFail(object.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }
                /**
     *意见反馈
     */
    public static void feekBack(Context context,String  content,final ICallback<Meta> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", UserUtil.getUserId(context)));
        mList.add(new Param("content", content));


        new MyAsyncTask(context, Urls.addSuggest, mList, new ICallback<String>() {

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
     *退款
     */
    public static void backMoney(Context context,String  money,String content,final ICallback<Meta> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", UserUtil.getUserId(context)));
        mList.add(new Param("content", content));
        mList.add(new Param("money", money));


        new MyAsyncTask(context, Urls.backMoney, mList, new ICallback<String>() {

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
     *我的保证金纪录
     */
    public static void protectlist(Context context,int p,final ICallback<ProtectRecordResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", UserUtil.getUserId(context)));
        mList.add(new Param("p", "" + p));


        new MyAsyncTask(context, Urls.protectlist, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "请求参数==" + result.toString());
                //{"houseList":null,"page":0,"status":0,"msg":"","sessionid":"40a5be61562f73454e372"}
                try {
                    JSONObject object = new JSONObject(result);
                    int status = object.getInt("status");
                    if(status==0){
                        int page = object.getInt("page");
                        if(page==0){
                            callback.onFail(Constants.JSON_HAS_NULL);
                        }else {
//                            GsonBuilder gb = new GsonBuilder();
//                            gb.registerTypeAdapter(String.class, new StringConverter());
//                            Gson gson = gb.create();
//                            DetailPlanResult meta = JsonUtil.parseObject(result, DetailPlanResult.class);
                            ProtectRecordResult meta = JsonUtil.parseObject(result, ProtectRecordResult.class);
                            callback.onSucceed(meta);

                        }
                    }else {
                        callback.onFail(object.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }

    /**
     *我接口
     */
    public static void myInfo(Context context,final ICallback<CraftsResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", UserUtil.getUserId(context)));


        new MyAsyncTask(context, Urls.myInfo, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                CraftsResult meta = JsonUtil.parseObject(result, CraftsResult.class);
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
     *修改我的信息
     */
    public static void myInfoEdit(Context context,String name,String age,String worktype,String workage,String cworkhome,String work,String adress,
                                  String bankName,String bankNum,String bankUserName,String referee,String price,String serverArea,String des,final ICallback<Meta> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", UserUtil.getUserId(context)));
        mList.add(new Param("name", name));
        mList.add(new Param("age", age));
        mList.add(new Param("worktype", worktype));
        mList.add(new Param("workage", workage));
        mList.add(new Param("work", work));
        mList.add(new Param("cworkhome", cworkhome));
        mList.add(new Param("adress", adress));
        mList.add(new Param("bankName", bankName));
        mList.add(new Param("bankNum", bankNum));
        mList.add(new Param("bankUserName", bankUserName));
        mList.add(new Param("referee", referee));
        mList.add(new Param("price", price));
        mList.add(new Param("serverArea", serverArea));
        mList.add(new Param("des", des));


        new MyAsyncTask(context, Urls.myInfoEdit, mList, new ICallback<String>() {

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
     *我的积分
     */
    public static void getMyScore(Context context,final ICallback<MyScore> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", UserUtil.getUserId(context)));

        new MyAsyncTask(context, Urls.getMyScore, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyScore meta = JsonUtil.parseObject(result, MyScore.class);
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
     *我的保证金纪录
     */
    public static void getScoreList(Context context,int p,final ICallback<ScoreResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", UserUtil.getUserId(context)));
        mList.add(new Param("p", "" + p));


        new MyAsyncTask(context, Urls.getScoreList, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "请求参数==" + result.toString());
                //{"houseList":null,"page":0,"status":0,"msg":"","sessionid":"40a5be61562f73454e372"}
                try {
                    JSONObject object = new JSONObject(result);
                    int status = object.getInt("status");
                    if(status==0){
                        int page = object.getInt("page");
                        if(page==0){
                            callback.onFail(Constants.JSON_HAS_NULL);
                        }else {
                            ScoreResult meta = JsonUtil.parseObject(result, ScoreResult.class);
                            callback.onSucceed(meta);

                        }
                    }else {
                        callback.onFail(object.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }

    /**
     *签到
     */
    public static void signIn(Context context,final ICallback<Meta> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", UserUtil.getUserId(context)));


        new MyAsyncTask(context, Urls.signIn, mList, new ICallback<String>() {

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
     传设备token 的接口
     */
    public static void addUmengDeviceToken(Context context,final ICallback<Meta> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", UserUtil.getUserId(context)));
        mList.add(new Param("deviceid", MobileOS.getIMEI(context)));
        mList.add(new Param("umeng", "umeng的token"));
        mList.add(new Param("phoneType", "1"));

        new MyAsyncTask(context, Urls.addUmengDeviceToken, mList, new ICallback<String>() {

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
     *签到
     */
    public static void getSignIn(Context context,final ICallback<SingInResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", UserUtil.getUserId(context)));


        new MyAsyncTask(context, Urls.getSignIn, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                SingInResult meta = JsonUtil.parseObject(result, SingInResult.class);
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
     *获取房屋信息
     */
    public static void getHouseInfo(Context context,String craftsId,String houseId,final ICallback<HouseInfoResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("craftsId", craftsId));
        mList.add(new Param("houseId", houseId));

        new MyAsyncTask(context, Urls.getHouseInfo, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                HouseInfoResult meta = JsonUtil.parseObject(result, HouseInfoResult.class);
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
     *工作类型获取工作列表
     */
    public static void getWorkList(Context context,String cid,int p,int worktype,final ICallback<WorkListResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", cid));
        mList.add(new Param("p", ""+p));
        mList.add(new Param("worktype", ""+worktype));
        mList.add(new Param("type", "1"));

        new MyAsyncTask(context, Urls.getWorkList, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                WorkListResult meta = JsonUtil.parseObject(result, WorkListResult.class);
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
     *工作详情
     */
    public static void getWorkDetail(Context context,String cid,String workId,final ICallback<WorkDetailResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", cid));
        mList.add(new Param("workId", workId));

        new MyAsyncTask(context, Urls.getWorkDetail, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                WorkDetailResult meta = JsonUtil.parseObject(result, WorkDetailResult.class);
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
     *工作详情（属于匠之家模块）
     */
    public static void myWorks(Context context,String cid,String finish,int p,final ICallback<MyWorksListResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", cid));
        mList.add(new Param("finish", finish));
        mList.add(new Param("p", "" + p));

        new MyAsyncTask(context, Urls.myWorks, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "请求参数==" + result.toString());
                JSONObject object = null;
                try {
                    object = new JSONObject(result);
                    int status = object.getInt("status");
                    if(result.contains("page")) {
                        JSONObject message = object.getJSONObject("message");
                        int page = message.getInt("page");
                        if (page == 0) {
                            callback.onFail(Constants.JSON_HAS_NULL);
                        } else {
                            MyWorksListResult meta = JsonUtil.parseObject(result, MyWorksListResult.class);
                            if (meta.getStatus() == 0) {
                                callback.onSucceed(meta);
                            } else {
                                callback.onFail(meta.getMsg());
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }

    public static void applyOder(Context context,String applyCraftsId,String workId,final ICallback<Meta> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("applyCraftsId", applyCraftsId));
        mList.add(new Param("workId", workId));

        new MyAsyncTask(context, Urls.applyOder, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "请求参数==" + result.toString());
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

    public static void myWorkDetail(Context context,String cid,String workId,String finish,final ICallback<MyWorkDetailResult> callback) {
        ArrayList<Param> mList = new ArrayList<Param>();
        mList.add(new Param("cid", cid));
        mList.add(new Param("workId", workId));
        mList.add(new Param("finish", finish));

        new MyAsyncTask(context, Urls.myWorkDetail, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {
                MyLog.e("", "请求参数==" + result.toString());
                MyWorkDetailResult meta = JsonUtil.parseObject(result, MyWorkDetailResult.class);
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