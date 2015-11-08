package app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import app.entity.Crafts;
import app.entity.Designer;
import app.entity.HouseInfo;
import app.entity.HouseInfoResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;

/**
 * Created by Administrator on 2015/10/12.
 */
public class BaseInfoFragment extends BaseFrament {

    private View layout;
    private ImageView ivHouse;
    private ImageView iv_house_designer;
    private ImageView iv_house_craft;

    private TextView tv_totalPrice;
    private TextView tv_designerPrice;
    private TextView tv_craftPrice;
    private TextView tv_houstype;
    private TextView tv_housearea;
    private TextView tv_housestyle;
    private TextView tv_craftmode;
    private TextView tv_designer_name;
    private TextView tv_craft_name;
    private TextView tv_house_age_workage;
    private TextView tv_house_age_workage_craft;
    private TextView tv_house_expert;
    private TextView tv_house_expert_craft;

    public static DisplayImageOptions options;
    private ImageLoader imageLoader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_base_info, null);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new RoundedBitmapDisplayer(0)).build();
        findViewById(layout);
        obtainData();
        return layout;
    }

    private void obtainData() {
        mWaitingDialog.show();
        HttpRequest.getHouseInfo(getActivity(), "100", "753665", new ICallback<HouseInfoResult>() {
            @Override
            public void onSucceed(HouseInfoResult result) {
                mWaitingDialog.dismiss();
                HouseInfo houseInfo = result.getHouse();
                if (houseInfo != null) {
                    setData(houseInfo);
                }

            }

            @Override
            public void onFail(String error) {
                mWaitingDialog.dismiss();
                Uihelper.showToast(getActivity(), error);

            }
        });

    }

    private void setData(HouseInfo houseInfo) {
        if (houseInfo != null) {

            imageLoader.displayImage(houseInfo.getImage(), ivHouse, options);
            tv_totalPrice.setText("合同总额：" + houseInfo.getTotal_price() + "元");
            tv_designerPrice.setText("设计合同：" + houseInfo.getDesign_price() + "元");
            tv_craftPrice.setText("施工合同：" + houseInfo.getCrafts_price() + "元");

            tv_houstype.setText(houseInfo.getType());
            tv_housearea.setText(houseInfo.getArea() + "㎡");
            tv_housestyle.setText(houseInfo.getStyle());
            tv_craftmode.setText(houseInfo.getCraft_mode());
        }

        Designer designer = houseInfo.getDesigner();
        if (designer != null) {
            tv_designer_name.setText(designer.getName());
            tv_house_age_workage.setText(designer.getAge() + "岁" + "/" + designer.getWork_age() + "年设计经验");
            tv_house_expert.setText(designer.getSkill());
        }
        Crafts craft = houseInfo.getCrafts();
        if (craft != null) {
            tv_craft_name.setText(craft.getName());
            tv_house_age_workage_craft.setText(craft.getAge() + "岁" + "/" + craft.getCworkold() + "年设计经验");
            tv_house_expert_craft.setText(craft.getExperts());
        }

    }

    private void findViewById(View layout) {

        ivHouse = (ImageView) layout.findViewById(R.id.iv_house);
        tv_totalPrice = (TextView) layout.findViewById(R.id.tv_totalPrice);
        tv_designerPrice = (TextView) layout.findViewById(R.id.tv_designerPrice);
        tv_craftPrice = (TextView) layout.findViewById(R.id.tv_craftPrice);

        tv_houstype = (TextView) layout.findViewById(R.id.tv_houstype);
        tv_housearea = (TextView) layout.findViewById(R.id.tv_housearea);
        tv_housestyle = (TextView) layout.findViewById(R.id.tv_housestyle);
        tv_craftmode = (TextView) layout.findViewById(R.id.tv_craftmode);

        tv_designer_name = (TextView) layout.findViewById(R.id.tv_designer_name);
        tv_craft_name = (TextView) layout.findViewById(R.id.tv_craft_name);

        tv_house_age_workage = (TextView) layout.findViewById(R.id.tv_house_age_workage);
        tv_house_age_workage_craft = (TextView) layout.findViewById(R.id.tv_house_age_workage_craft);

        tv_house_expert = (TextView) layout.findViewById(R.id.tv_house_expert);
        tv_house_expert_craft = (TextView) layout.findViewById(R.id.tv_house_expert_craft);


        iv_house_designer = (ImageView) layout.findViewById(R.id.iv_house_designer);
        iv_house_craft = (ImageView) layout.findViewById(R.id.iv_house_craft);

    }
}
