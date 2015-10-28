package app.entity;

/**
 * Created by Administrator on 2015/10/10.
 */
public class UserInfo {


    private static UserInfo userInfo = null;
    public static UserInfo getInstance(){

        if(userInfo==null){
            userInfo = new UserInfo();
        }

        return userInfo;
    }


    private UserInfo(){

    }


    private String score;
    private String busername;
    private String protect;
    private String realname;
    private String mid;
    private String work_tedian;
    private String work_say;
    private String work_text;
    private String card_bimg;
    private String card_img;
    private String cworknum;
    private String cworkmobile;
    private String cworkpw;
    private String address;
    private String charge_type;
    private String cworkhome;
    private String experts;
    private String cworkweixin;
    private String cworkalipay;
    private String role;
    private String cworkbname;
    private String cworkbank;
    private boolean hasinfo;
    private String deviceid;
    private String umeng;
    private String truecwork;
    private String cworktime;
    private String cworkserver;
    private String serverprice;
    private String cworktype;
    private String cimg;
    private String referee;
    private String cname;
    private String south;
    private String north;
    private String lastlogin;
    private String jiang;
    private String name;
    private String id;
    private String gender;
    private String description;
    private String price;
    private String server_area;
    private String vip;
    private String gongyi;
    private String renzheng;
    private String baozhengjin;
    private String cra_groupid;
    private String cra_group;
    private String is_show;
    private String sort;
    private String profession;
    private String qualifications;
    private String overallattitude;
    private String overallquality;

    // TODO: 2015/10/18


    @Override
    public String toString() {
        return "UserInfo{" +
                "score='" + score + '\'' +
                ", busername='" + busername + '\'' +
                ", protect='" + protect + '\'' +
                ", realname='" + realname + '\'' +
                ", mid='" + mid + '\'' +
                ", work_tedian='" + work_tedian + '\'' +
                ", work_say='" + work_say + '\'' +
                ", work_text='" + work_text + '\'' +
                ", card_bimg='" + card_bimg + '\'' +
                ", card_img='" + card_img + '\'' +
                ", cworknum='" + cworknum + '\'' +
                ", cworkmobile='" + cworkmobile + '\'' +
                ", cworkpw='" + cworkpw + '\'' +
                ", address='" + address + '\'' +
                ", charge_type='" + charge_type + '\'' +
                ", cworkhome='" + cworkhome + '\'' +
                ", experts='" + experts + '\'' +
                ", cworkweixin='" + cworkweixin + '\'' +
                ", cworkalipay='" + cworkalipay + '\'' +
                ", role='" + role + '\'' +
                ", cworkbname='" + cworkbname + '\'' +
                ", cworkbank='" + cworkbank + '\'' +
                ", hasinfo=" + hasinfo +
                ", deviceid='" + deviceid + '\'' +
                ", umeng='" + umeng + '\'' +
                ", truecwork='" + truecwork + '\'' +
                ", cworktime='" + cworktime + '\'' +
                ", cworkserver='" + cworkserver + '\'' +
                ", serverprice='" + serverprice + '\'' +
                ", cworktype='" + cworktype + '\'' +
                ", cimg='" + cimg + '\'' +
                ", referee='" + referee + '\'' +
                ", cname='" + cname + '\'' +
                ", south='" + south + '\'' +
                ", north='" + north + '\'' +
                ", lastlogin='" + lastlogin + '\'' +
                ", jiang='" + jiang + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", gender='" + gender + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", server_area='" + server_area + '\'' +
                ", vip='" + vip + '\'' +
                ", gongyi='" + gongyi + '\'' +
                ", renzheng='" + renzheng + '\'' +
                ", baozhengjin='" + baozhengjin + '\'' +
                ", cra_groupid='" + cra_groupid + '\'' +
                ", cra_group='" + cra_group + '\'' +
                ", is_show='" + is_show + '\'' +
                ", sort='" + sort + '\'' +
                ", profession='" + profession + '\'' +
                ", qualifications='" + qualifications + '\'' +
                ", overallattitude='" + overallattitude + '\'' +
                ", overallquality='" + overallquality + '\'' +
                '}';
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getBusername() {
        return busername;
    }

    public void setBusername(String busername) {
        this.busername = busername;
    }

    public String getProtect() {
        return protect;
    }

    public void setProtect(String protect) {
        this.protect = protect;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getWork_tedian() {
        return work_tedian;
    }

    public void setWork_tedian(String work_tedian) {
        this.work_tedian = work_tedian;
    }

    public String getWork_say() {
        return work_say;
    }

    public void setWork_say(String work_say) {
        this.work_say = work_say;
    }

    public String getWork_text() {
        return work_text;
    }

    public void setWork_text(String work_text) {
        this.work_text = work_text;
    }

    public String getCard_bimg() {
        return card_bimg;
    }

    public void setCard_bimg(String card_bimg) {
        this.card_bimg = card_bimg;
    }

    public String getCard_img() {
        return card_img;
    }

    public void setCard_img(String card_img) {
        this.card_img = card_img;
    }

    public String getCworknum() {
        return cworknum;
    }

    public void setCworknum(String cworknum) {
        this.cworknum = cworknum;
    }

    public String getCworkmobile() {
        return cworkmobile;
    }

    public void setCworkmobile(String cworkmobile) {
        this.cworkmobile = cworkmobile;
    }

    public String getCworkpw() {
        return cworkpw;
    }

    public void setCworkpw(String cworkpw) {
        this.cworkpw = cworkpw;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCharge_type() {
        return charge_type;
    }

    public void setCharge_type(String charge_type) {
        this.charge_type = charge_type;
    }

    public String getCworkhome() {
        return cworkhome;
    }

    public void setCworkhome(String cworkhome) {
        this.cworkhome = cworkhome;
    }

    public String getExperts() {
        return experts;
    }

    public void setExperts(String experts) {
        this.experts = experts;
    }

    public String getCworkweixin() {
        return cworkweixin;
    }

    public void setCworkweixin(String cworkweixin) {
        this.cworkweixin = cworkweixin;
    }

    public String getCworkalipay() {
        return cworkalipay;
    }

    public void setCworkalipay(String cworkalipay) {
        this.cworkalipay = cworkalipay;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCworkbname() {
        return cworkbname;
    }

    public void setCworkbname(String cworkbname) {
        this.cworkbname = cworkbname;
    }

    public String getCworkbank() {
        return cworkbank;
    }

    public void setCworkbank(String cworkbank) {
        this.cworkbank = cworkbank;
    }

    public boolean isHasinfo() {
        return hasinfo;
    }

    public void setHasinfo(boolean hasinfo) {
        this.hasinfo = hasinfo;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getUmeng() {
        return umeng;
    }

    public void setUmeng(String umeng) {
        this.umeng = umeng;
    }

    public String getTruecwork() {
        return truecwork;
    }

    public void setTruecwork(String truecwork) {
        this.truecwork = truecwork;
    }

    public String getCworktime() {
        return cworktime;
    }

    public void setCworktime(String cworktime) {
        this.cworktime = cworktime;
    }

    public String getCworkserver() {
        return cworkserver;
    }

    public void setCworkserver(String cworkserver) {
        this.cworkserver = cworkserver;
    }

    public String getServerprice() {
        return serverprice;
    }

    public void setServerprice(String serverprice) {
        this.serverprice = serverprice;
    }

    public String getCworktype() {
        return cworktype;
    }

    public void setCworktype(String cworktype) {
        this.cworktype = cworktype;
    }

    public String getCimg() {
        return cimg;
    }

    public void setCimg(String cimg) {
        this.cimg = cimg;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getJiang() {
        return jiang;
    }

    public void setJiang(String jiang) {
        this.jiang = jiang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getServer_area() {
        return server_area;
    }

    public void setServer_area(String server_area) {
        this.server_area = server_area;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getGongyi() {
        return gongyi;
    }

    public void setGongyi(String gongyi) {
        this.gongyi = gongyi;
    }

    public String getRenzheng() {
        return renzheng;
    }

    public void setRenzheng(String renzheng) {
        this.renzheng = renzheng;
    }

    public String getBaozhengjin() {
        return baozhengjin;
    }

    public void setBaozhengjin(String baozhengjin) {
        this.baozhengjin = baozhengjin;
    }

    public String getCra_groupid() {
        return cra_groupid;
    }

    public void setCra_groupid(String cra_groupid) {
        this.cra_groupid = cra_groupid;
    }

    public String getCra_group() {
        return cra_group;
    }

    public void setCra_group(String cra_group) {
        this.cra_group = cra_group;
    }

    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getOverallattitude() {
        return overallattitude;
    }

    public void setOverallattitude(String overallattitude) {
        this.overallattitude = overallattitude;
    }

    public String getOverallquality() {
        return overallquality;
    }

    public void setOverallquality(String overallquality) {
        this.overallquality = overallquality;
    }
}
