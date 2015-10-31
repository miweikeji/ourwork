package app.tools;

import org.json.JSONException;
import org.json.JSONObject;

import app.entity.UserInfo;

/**
 * Created by Administrator on 2015/10/31.
 */
public class UserInfoJsonTools {

    public  static void jsonUserInfo(String json){

        try {
            UserInfo info = UserInfo.getInstance();

            JSONObject object = new JSONObject(json);
            JSONObject s = object.getJSONObject("crafts");
            info.setAddress(s.getString("address"));
            info.setBaozhengjin(s.getString("baozhengjin"));
            info.setBusername(s.getString("busername"));
            info.setCard_bimg(s.getString("card_bimg"));
            info.setCard_img(s.getString("card_img"));
            info.setCharge_type(s.getString("charge_type"));
            info.setCimg(s.getString("cimg"));
            info.setCname(s.getString("cname"));
            info.setCra_group(s.getString("cra_group"));
            info.setCra_groupid(s.getString("cra_groupid"));
            info.setCworkalipay(s.getString("cworkalipay"));
            info.setName(s.getString("name"));
            info.setCworkbank(s.getString("cworkbank"));
            info.setCworkbname(s.getString("cworkbname"));
            info.setCworkmobile(s.getString("cworkmobile"));
            info.setCworkhome(s.getString("cworkhome"));
            info.setCworknum(s.getString("cworknum"));
            info.setCworkpw(s.getString("cworkpw"));
            info.setCworkserver(s.getString("cworkserver"));
            info.setCworktime(s.getString("cworktime"));
            info.setCworktype(s.getString("cworktype"));

            info.setCworkweixin(s.getString("cworkweixin"));
            info.setProtect(s.getString("protect"));
            info.setRealname(s.getString("realname"));
            info.setMid(s.getString("mid"));
            info.setWork_tedian(s.getString("work_tedian"));
            info.setWork_say(s.getString("work_say"));
            info.setWork_text(s.getString("work_text"));
            info.setExperts(s.getString("experts"));
            info.setRole(s.getString("role"));
            info.setHasinfo(s.getBoolean("hasinfo"));
            info.setDeviceid(s.getString("deviceid"));
            info.setUmeng(s.getString("umeng"));
            info.setTruecwork(s.getString("truecwork"));
            info.setServerprice(s.getString("serverprice"));
            info.setReferee(s.getString("referee"));
            info.setSouth(s.getString("south"));
            info.setNorth(s.getString("north"));
            info.setLastlogin(s.getString("lastlogin"));
            info.setJiang(s.getString("jiang"));
            info.setGender(s.getString("gender"));
            info.setPrice(s.getString("price"));
            info.setServer_area(s.getString("server_area"));
            info.setVip(s.getString("vip"));
            info.setGongyi(s.getString("gongyi"));
            info.setRenzheng(s.getString("renzheng"));
            info.setIs_show(s.getString("is_show"));
            info.setSort(s.getString("sort"));
            info.setQualifications(s.getString("qualifications"));
            info.setOverallattitude(s.getString("overallattitude"));
            info.setOverallquality(s.getString("overallquality"));
            info.setProfession(s.getString("profession"));
            info.setScore(s.getString("score"));
            info.setDescription(s.getString("description"));
            info.setId(s.getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }





}
