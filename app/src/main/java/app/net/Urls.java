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
    public final static String register = base_server+"App/Signup/register";
    //登录
    public final static String login = base_server+"App/Signin/login";
    //提交工匠基本信息接口
    public final static String BasicInfo = base_server+"App/Crafts/createCraftsBasicInfo";
    //获取工匠信息
    public final static String craftsman_info=base_server+"App/Crafts/getCraftsInfoRelevant";
    //获取工匠参与的所有案例
    public final static String all_case = base_server+"App/House/getHouseListByCraftsId";

    //获取班组接口
    public final static String getCraGroup=base_server+"App/Cragroup/getCraGroup";
    //班组成员接口
    public final static String getGroupCrafts=base_server+"App/Cragroup/getGroupCrafts";
    //班组创建者接口
    public final static String getGroupGanger=base_server+"App/Cragroup/getGroupGanger";

    //找回密码
    public final static String forgetPassword = base_server+"App/Signup/forgetPassword";
    //预约中列表接口
    public final static String getHousesByLyf = base_server+"App/House/getHousesByLyf";
    //确定预约列表接口
    public final static String getHousesByAppointmentLyf = base_server+"App/House/getHousesByAppointmentLyf";
    //预约历史列表接口
    public final static String getHousesByHistoryLyf = base_server+"App/House/getHousesByHistoryLyf";
    //婉拒预约接口
    public final static String refuseAppointmentLyf = base_server+"App/House/refuseAppointmentLyf";
    //获取接受预约接口
    public final static String acceptAppointmentLyf = base_server+"App/House/acceptAppointmentLyf";
    //所有工友
    public final static String getAllcrafts = base_server+"App/Crafts/getAllcrafts";
    //好友接口
    public final static String getMyfriend  = base_server+"App/Crafts/getMyfriend";
    //未安排任务接口
    public final static String unArrangeTask  = base_server+"App/Workplan/unArrangeTask";
    //广告
    public final static String advertise  = base_server+"/App/Server";
    //工头认证
    public final static String workHeadAudit  = base_server+"/App/Crafts/workHeadAudit";
    //添加班组成员
    public final static String addGroupCrafts  = base_server+"/App/Cragroup/addGroupCrafts";
    //根据工种类型查询工匠列表
    public final static String searchcraftsByType  = base_server+"App/Crafts/searchcraftsByType";


}
