package app.net;

/**
 * Created by Administrator on 2015/9/4.
 */
public class Urls {
    //测试服务器
    public static final String base_server = "http://test.miweikj.cn/";

    //正式服务器
//    public static final String base_server = "http://www.miweikj.cn/";
    //    public static final String base_server = "http://172.20.8.143:8080/miqian-app/";
    //    public static final String base_server = "http://172.20.8.11:8082/miqian-app/";

    //测试
    public final static String test = base_server + "App/Signup/getMsgCode";
    public final static String register = base_server+"App/Signup/register";
    public final static String login = base_server+"App/Signin/login";
    public final static String BasicInfo = base_server+"App/Crafts/createCraftsBasicInfo";

}
