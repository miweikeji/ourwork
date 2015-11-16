package app.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miwei.jzj_system.R;

import java.util.List;

import app.activity.ChangeTasksActivity;
import app.dialog.DialogTools;
import app.entity.Cinfo;
import app.entity.Cname;
import app.entity.HouseData;
import app.entity.Info;
import app.tools.StatusTools;

/**
 * Created by Administrator on 2015/11/6.
 */
public class ChangeTaskAdapter extends AllAdapter {


    removeItem removeListens;
    removeNameItem removeNameListens;
    addNameItem addNameListens;
    billingTypeItem billingTypeListens;
    addCaseNmae addCaseNmaeListens;
    changeTime changeTimeListens;
    moneyItem moneyItemListens;

    public void setChangeTimeListens(changeTime changeTimeListens) {
        this.changeTimeListens = changeTimeListens;
    }
    public void setmoneyItem(moneyItem moneyItemListens) {
        this.moneyItemListens = moneyItemListens;
    }

    public void setData(List<HouseData> infoList) {
        this.infoList = infoList;
    }

    public interface removeItem{
        void removePosition(int position,String wtype);
    }
    public interface moneyItem{
        void moneyItemPosition(int position,String money,String wtype);
    }
    public interface changeTime{
        void changeTimePosition(int position,String wtype);
    }
    public interface addCaseNmae{
        void addCaseNmaePosition(int position,String wtype,int id);
    }

    public interface removeNameItem{
        void removeNamePosition(int position,String wtype,int id);
    }
    public interface addNameItem{
        void addNamePosition(int position,String wtype);
    }
    public interface billingTypeItem{
        void billingTypePosition(int position,String wtype,String type);
    }

    public void setBillingTypeListens(billingTypeItem billingTypeListens) {
        this.billingTypeListens = billingTypeListens;
    }

    public void setAddCaseNmaeListens(addCaseNmae addCaseNmaeListens) {
        this.addCaseNmaeListens = addCaseNmaeListens;
    }

    public void setRemoveNameListens(removeNameItem removeNameListens) {
        this.removeNameListens = removeNameListens;
    }

    public void setAddNameListens(addNameItem addNameListens) {
        this.addNameListens = addNameListens;
    }

    public void setRemoveListens(removeItem removeListens) {
        this.removeListens = removeListens;
    }

    private ChangeTasksActivity activity;
    private  List<HouseData> infoList;
    public ChangeTaskAdapter(ChangeTasksActivity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return infoList==null?0:infoList.size();
    }

    @Override
    public View getView(final int position, View layout, ViewGroup parent) {
        ViewHolder holder=null;
        if(layout==null){
            holder = new ViewHolder();
            layout = activity.getLayoutInflater().inflate(R.layout.item_choose_case, null);
            holder.rl_to_time = (RelativeLayout) layout.findViewById(R.id.rl_to_time);
            holder.rl_prices = (RelativeLayout) layout.findViewById(R.id.rl_prices);
            holder.et_price = (TextView) layout.findViewById(R.id.et_price);
            holder.et_name= (TextView) layout.findViewById(R.id.et_name);
            holder.rl_biling_type = (RelativeLayout) layout.findViewById(R.id.rl_biling_type);
            holder.rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
            holder.rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
            holder.tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
            holder.tv_deleate_case = (TextView) layout.findViewById(R.id.tv_deleate_case);
            holder.tv_biling_type= (TextView) layout.findViewById(R.id.tv_biling_type);
            holder.add_item= (LinearLayout) layout.findViewById(R.id.add_item);
            holder.tv_case_title = (TextView) layout.findViewById(R.id.tv_case_title);
            holder.tv_wtype = (TextView) layout.findViewById(R.id.tv_wtype);
            layout.setTag(holder);
        }else {
             holder = (ViewHolder) layout.getTag();
        }
        final HouseData info = this.infoList.get(position);
        holder.tv_biling_type.setText(StatusTools.setChargeType(info.getChargetype()));
        holder.et_price.setText(info.getCharge());
        List<Cname> name = info.getName();
        holder.tv_case_title.setText(StatusTools.workType(info.getWtype()));
        holder.tv_case_type.setText(StatusTools.workTypes(info.getWtype()));
        int num = Integer.valueOf(info.getNum());
        holder.add_item.removeAllViews();
        for (int i=0;i<num;i++){
            View view = activity.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
            RelativeLayout rl_del_case = (RelativeLayout) view.findViewById(R.id.rl_del_case);
            RelativeLayout rl_invitation = (RelativeLayout) view.findViewById(R.id.rl_invitation_);
            TextView tv_case_type = (TextView) view.findViewById(R.id.tv_case_type);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            rl_del_case.setId(i);
            if(i<name.size()){
                Cname cname = name.get(i);
                tv_name.setText(cname.getName());
            }else {
                tv_name.setText("通过平台找工匠");
            }
            tv_case_type.setText(StatusTools.workTypes(info.getWtype()));
            rl_invitation.setId(i);
            rl_invitation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addCaseNmaeListens.addCaseNmaePosition(position,info.getWtype(),v.getId());
                }
            });
            rl_del_case.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeNameListens.removeNamePosition(position,info.getWtype(),v.getId());
                }
            });
            holder.add_item.addView(view);
        }

        holder.tv_deleate_case.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeListens.removePosition(position, info.getWtype());

            }
        });
        holder.rl_add_case.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNameListens.addNamePosition(position, info.getWtype());
            }
        });
        holder.rl_biling_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogTools.billingType(activity, Integer.valueOf(info.getWtype()).intValue()).show();
                DialogTools.setTypeChoose(new DialogTools.DialogCountTypeListens() {
                    @Override
                    public void onTypeChoose(String type, int i) {
                        billingTypeListens.billingTypePosition(position, "" + i, type);
                    }
                });
            }
        });
        holder.rl_to_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTimeListens.changeTimePosition(position, info.getWtype());
            }
        });

        holder.rl_prices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyItemListens.moneyItemPosition(position,"",info.getWtype());
            }
        });

        return layout;
    }

    public class  ViewHolder{
        RelativeLayout rl_to_time;
        RelativeLayout rl_prices;
        TextView et_price;
        TextView et_name;
        RelativeLayout rl_biling_type;
        RelativeLayout rl_add_case;
        RelativeLayout rl_invitation;
        TextView tv_case_type;
        TextView tv_deleate_case;
        TextView tv_biling_type;
        LinearLayout add_item;
        TextView tv_case_title;
        TextView tv_wtype;
    }
}
