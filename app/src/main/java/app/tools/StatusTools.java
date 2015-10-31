package app.tools;

import android.widget.TextView;

/**
 * Created by Administrator on 2015/10/27.
 */
public class StatusTools {

    public static void setStatus(TextView tv_status, String status) {
        if("0".equals(status)){
            tv_status.setText("量房阶段");
        }else if("1".equals(status)){
            tv_status.setText("验房阶段");
        }else if("2".equals(status)){
            tv_status.setText("设计阶段");
        }else if("3".equals(status)){
            tv_status.setText("合同阶段");
        }else if("4".equals(status)){
            tv_status.setText("主体拆改阶段");
        }else if("5".equals(status)){
            tv_status.setText("水电工程阶段");
        }else if("6".equals(status)){
            tv_status.setText("泥瓦工程阶段");
        }else if("7".equals(status)){
            tv_status.setText("木作工程阶段");
        }else if("8".equals(status)){
            tv_status.setText("油漆工程阶段");
        }else if("9".equals(status)){
            tv_status.setText("设备安装阶段");
        }else if("10".equals(status)){
            tv_status.setText("清洁验收阶段");
        }else if("11".equals(status)){
            tv_status.setText("竣工阶段");
        }
    }

    public static void workType(TextView view,String type){
        //1水电工  2 泥水工 3 木工 4  油漆工5  门窗安装工  6敲打搬运工
        if("1".equals(type)){
            view.setText("水电工");
        }else if("2".equals(type)){
            view.setText("泥水工");
        }else if("3".equals(type)){
            view.setText("木工");
        }else if("4".equals(type)){
            view.setText("油漆工");
        }else if("5".equals(type)){
            view.setText("门窗安装工");
        }else if("6".equals(type)){
            view.setText("敲打搬运工");
        }
    }

    public static String workType(String type){
        //1水电工  2 泥水工 3 木工 4  油漆工5  门窗安装工  6敲打搬运工
        if("1".equals(type)){
            return "水电工";
        }else if("2".equals(type)){
            return "泥水工";
        }else if("3".equals(type)){
            return "木工";
        }else if("4".equals(type)){
            return "油漆工";
        }else if("5".equals(type)){
            return "门窗安装工";
        }else if("6".equals(type)){
            return "敲打搬运工";
        }
        return "";
    }

    public static int getWorkType(String type){

        if("水电工".equals(type)){
            return 1;
        }else if("泥水工".equals(type)){
            return 2;
        }else if("木工".equals(type)){
            return 3;
        }else if("油漆工".equals(type)){
            return 4;
        }else if("门窗安装工".equals(type)){
            return 5;
        }else if("敲打搬运工".equals(type)){
            return 6;
        }
        return 1;
    }

    public static String Json = "{\"list\":[{\"cid\":\"0\",\"worktype\":\"3\",\"datatime\":\"1441813729933\",\"pm\":\"1\",\"am\":\"0\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"845\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"},{\"cid\":\"0\",\"worktype\":\"3\",\"datatime\":\"1441900129933\",\"pm\":\"0\",\"am\":\"1\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"846\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"},{\"cid\":\"0\",\"worktype\":\"3\",\"datatime\":\"1441986529933\",\"pm\":\"0\",\"am\":\"0\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"847\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"},{\"cid\":\"0\",\"worktype\":\"3\",\"datatime\":\"1442072929933\",\"pm\":\"0\",\"am\":\"0\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"848\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"},{\"cid\":\"0\",\"worktype\":\"3\",\"datatime\":\"1442159329933\",\"pm\":\"0\",\"am\":\"0\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"849\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"},{\"cid\":\"0\",\"worktype\":\"3\",\"datatime\":\"1442245729933\",\"pm\":\"0\",\"am\":\"0\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"850\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"},{\"cid\":\"0\",\"worktype\":\"3\",\"datatime\":\"1442332129933\",\"pm\":\"0\",\"am\":\"0\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"851\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"},{\"cid\":\"0\",\"worktype\":\"4\",\"datatime\":\"1441813733289\",\"pm\":\"0\",\"am\":\"1\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"852\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"},{\"cid\":\"0\",\"worktype\":\"4\",\"datatime\":\"1441900133289\",\"pm\":\"0\",\"am\":\"1\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"853\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"},{\"cid\":\"0\",\"worktype\":\"4\",\"datatime\":\"1441986533289\",\"pm\":\"0\",\"am\":\"0\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"854\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"},{\"cid\":\"0\",\"worktype\":\"4\",\"datatime\":\"1442072933289\",\"pm\":\"0\",\"am\":\"0\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"855\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"},{\"cid\":\"0\",\"worktype\":\"4\",\"datatime\":\"1442159333289\",\"pm\":\"0\",\"am\":\"0\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"856\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"},{\"cid\":\"0\",\"worktype\":\"4\",\"datatime\":\"1442245733289\",\"pm\":\"0\",\"am\":\"0\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"857\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"},{\"cid\":\"0\",\"worktype\":\"4\",\"datatime\":\"1442332133289\",\"pm\":\"0\",\"am\":\"0\",\"workplace\":\"华林花园\",\"time\":\"0\",\"id\":\"858\",\"servertype\":\"0\",\"servertime\":\"88\",\"type\":\"1\",\"craftsImg\":\"\",\"name\":\"\",\"workType\":\"\",\"profession\":\"\"}],\"page\":1,\"status\":0,\"msg\":\"\",\"sessionid\":\"76bf63fd562f94ea2830";
}
