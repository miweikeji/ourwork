package app.net;

/**
 * Created by Administrator on 2015/9/4.
 */
public class Urls {
    //测试服务器
    public static final String base_server = "http://test.miweikj.cn/";

    //正式服务器
//    public static final String base_server = "http://www.miweikj.cn/";

    //发送验证码
    public final static String getMsgCode = base_server + "App/Signup/getMsgCode";
    //注册
    public final static String register = base_server + "App/Signup/register";
    //登录
    public final static String login = base_server + "App/Signin/login";
    //提交工匠基本信息接口
    public final static String BasicInfo = base_server + "App/Crafts/createCraftsBasicInfo";
    //获取工匠信息
    public final static String craftsman_info = base_server + "App/Crafts/getCraftsInfoRelevant";
    //获取工匠参与的所有案例
    public final static String all_case = base_server + "App/House/getHouseListByCraftsId";
    //获取班组接口
    public final static String getCraGroup = base_server + "App/Cragroup/getCraGroup";
    //班组成员接口
    public final static String getGroupCrafts = base_server + "App/Cragroup/getGroupCrafts";
    //班组创建者接口
    public final static String getGroupGanger = base_server + "App/Cragroup/getGroupGanger";

    //找回密码
    public final static String forgetPassword = base_server + "App/Signup/forgetPassword";
    //预约中列表接口
    public final static String getHousesByLyf = base_server + "App/House/getHousesByLyf";
    //确定预约列表接口
    public final static String getHousesByAppointmentLyf = base_server + "App/House/getHousesByAppointmentLyf";
    //预约历史列表接口
    public final static String getHousesByHistoryLyf = base_server + "App/House/getHousesByHistoryLyf";
    //婉拒预约接口
    public final static String refuseAppointmentLyf = base_server + "App/House/refuseAppointmentLyf";
    //获取接受预约接口
    public final static String acceptAppointmentLyf = base_server + "App/House/acceptAppointmentLyf";
    //所有工友
    public final static String getAllcrafts = base_server + "App/Crafts/getAllcrafts";
    //好友接口
    public final static String getMyfriend = base_server + "App/Crafts/getMyfriend";
    //未安排任务接口
    public final static String unArrangeTask = base_server + "App/Workplan/unArrangeTask";
    //广告
    public final static String advertise = base_server + "App/Server";
    //添加班组成员
    public final static String addGroupCrafts = base_server + "App/Cragroup/addGroupCrafts";
    //根据工种类型查询工匠列表
    public final static String searchcraftsByType = base_server + "App/Crafts/searchcraftsByType";
    //工作安排施工中
    public final static String constructPlan = base_server + "App/Workplan/constructPlan";
    //工作安排完成
    public final static String finishPlan = base_server + "App/Workplan/finishPlan";
    //工作安排详情接口
    public final static String detailPlan = base_server + "App/Workplan/detailPlan";
    //获取已建计划接口
    public final static String getTask = base_server + "App/Workplan/getTask";


   /* ============================================我的接口==开始=================================*/

    public final static String addSuggest = base_server + "App/Suggest/add";
    //退款接口
    public final static String backMoney = base_server + "App/Union/back";
    //我的保障金记录接口
    public final static String protectlist = base_server + "App/Protect/protectlist";
    //我接口
    public final static String myInfo = base_server + "App/Crafts/myInfo";
    //修改我的信息
    public final static String myInfoEdit = base_server + "App/Crafts/myInfoEdit";
    //我的积分
    public final static String getMyScore = base_server + "App/Score/getMyScore";
    //我的积分列表
    public final static String getScoreList = base_server + "App/Score/getScoreList";
    //签到
    public final static String signIn = base_server + "App/Signin/signIn";
    //获取是否签到
    public final static String getSignIn = base_server + "App/Signin/getSignIn";
    //工头认证
    public final static String workHeadAudit = base_server + "App/Crafts/workHeadAudit";
    //友盟推送devicetoken
    public final static String addUmengDeviceToken = base_server + "App/Crafts/addUmengDeviceToken";

     /* ============================================我的接口结束=================================*/

    //房屋信息接口
    public final static String getHouseInfo = base_server + "App/House/getHouseInfo";

    //工作类型获取工作列表
    public final static String getWorkList = base_server + "App/Work/getWorkList";

    //工作详情(属于工作机会模块)
    public final static String getWorkDetail = base_server + "App/Work/getWorkDetail";

    //我的工作接口（施工中、已完工）（属于我的工作模块）
    public final static String myWorkDetail = base_server + "App/Work/myWorkDetail";

    //工作详情（属于匠之家模块）
    public final static String myWorks = base_server + "App/Work/myWorks";


}
