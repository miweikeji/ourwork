package app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import app.entity.Crafts;
import app.entity.CraftsResult;
import app.entity.GroupInfo;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.CircleBitmapDisplayer;

/**
 * Created by Administrator on 2015/10/12.
 */
public class CraftsmanInfoFragment extends Fragment {

    private RelativeLayout rl_normal;//普通工匠
    private RelativeLayout rl_foreman;//帶班班長
    private RelativeLayout rl_is_foreman;
    private ImageView userImage;
    private TextView age;
    private TextView jobAge;
    private TextView jobType;
    private TextView area;
    private TextView foremanName;
    private TextView normalName;
    private TextView areaServer;
    private TextView introduction;
    private TextView oldCase;
    private TextView ordinaryVIP;
    private TextView technology;
    private TextView Authentication;
    private TextView tv_gold;
    private TextView tv_my_craftsman_group;
    private TextView tv_creat_time;
    private TextView tv_group_num;
    private TextView constructionType;
    private TextView tv_style;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private RatingBar ratingBarQuality;
    private RatingBar ratingBarValue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_craftsman_info, null);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.test).cacheInMemory(true).cacheOnDisk(true)
                .displayer(new CircleBitmapDisplayer()).build();
        initUI(layout);
        netWorkData();
        return layout;
    }

    private void netWorkData() {
        HttpRequest.craftsmanInfoHttp(getActivity(), "106", new ICallback<CraftsResult>() {
            @Override
            public void onSucceed(CraftsResult result) {
                Crafts crafts = result.getCrafts();
                int jiang = crafts.getJiang();
                if(jiang==0){
                    rl_foreman.setVisibility(View.VISIBLE);
                    rl_normal.setVisibility(View.INVISIBLE);
                    GroupInfo groupInfo = result.getCrafts().getGroupInfo();
                    tv_style.setText("装修风格："+groupInfo.getExpert());
                    constructionType.setText("施工方式"+groupInfo.getStyle());
                    tv_group_num.setText("工匠队伍："+groupInfo.getCount()+"人");
                    tv_creat_time.setText("" + groupInfo.getCreate_time());
                    tv_my_craftsman_group.setText(groupInfo.getName() + "工匠班组");

                }else{
                    rl_foreman.setVisibility(View.INVISIBLE);
                    rl_normal.setVisibility(View.VISIBLE);
                    rl_is_foreman.setVisibility(View.GONE);
                }
                normalName.setText(crafts.getName());
                introduction.setText(crafts.getDescription());
                areaServer.setText(crafts.getServer_area());
                oldCase.setText(crafts.getWork());

                foremanName.setText(crafts.getName());
                age.setText(""+crafts.getAge()+"岁");
                area.setText(crafts.getCworkhome());
                jobAge.setText(""+crafts.getCworkold());
                jobType.setText(crafts.getProfession());
                imageLoader.displayImage(crafts.getCimg(), userImage, options);
                ratingBarValue.setRating(crafts.getOverallattitude());
                ratingBarQuality.setRating(crafts.getOverallquality());
                if (!TextUtils.isEmpty(crafts.getVip())){
                    ordinaryVIP.setText(crafts.getVip());
                } if (!TextUtils.isEmpty(crafts.getGongyi())){
                    technology.setText(crafts.getGongyi());
                } if (!TextUtils.isEmpty(crafts.getBaozhengjin())){
                    tv_gold.setText(crafts.getBaozhengjin());
                } if (!TextUtils.isEmpty(crafts.getRenzheng())){
                    Authentication.setText(crafts.getRenzheng());
                }
            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(getActivity(),error);
            }
        });
    }

    private void initUI(View layout) {
        rl_normal = (RelativeLayout) layout.findViewById(R.id.rl_normal);
        rl_foreman = (RelativeLayout) layout.findViewById(R.id.rl_foreman);
        areaServer = (TextView) layout.findViewById(R.id.tv_area_server);
        rl_is_foreman = (RelativeLayout) layout.findViewById(R.id.rl_is_foreman);
        oldCase = (TextView) layout.findViewById(R.id.tv_ole_case);

        userImage = (ImageView) layout.findViewById(R.id.iv_me_userimage);
        area = (TextView) layout.findViewById(R.id.tv_me_area);
        age = (TextView) layout.findViewById(R.id.tv_me_age);
        jobAge = (TextView) layout.findViewById(R.id.tv_me_jobage);
        jobType = (TextView) layout.findViewById(R.id.tv_me_job);
        foremanName = (TextView) layout.findViewById(R.id.tv_me_name);

        ordinaryVIP = (TextView) layout.findViewById(R.id.tv_Ordinary_vip);
        introduction = (TextView) layout.findViewById(R.id.tv_me_Introduction);
        technology = (TextView) layout.findViewById(R.id.tv_Technology);
        normalName = (TextView) layout.findViewById(R.id.tv_normal_name);
        tv_gold = (TextView) layout.findViewById(R.id.tv_gold);
        Authentication = (TextView) layout.findViewById(R.id.tv_Authentication);

        tv_my_craftsman_group = (TextView) layout.findViewById(R.id.tv_my_craftsman_group);
        tv_creat_time = (TextView) layout.findViewById(R.id.tv_creat_time);
        tv_group_num = (TextView) layout.findViewById(R.id.tv_group_num);
        constructionType = (TextView) layout.findViewById(R.id.tv_Construction_type);
        tv_style = (TextView) layout.findViewById(R.id.tv_style);

        ratingBarQuality = (RatingBar)layout.findViewById(R.id.ratingBar_quality);
        ratingBarValue = (RatingBar) layout.findViewById(R.id.ratingBar_value);
        ratingBarQuality.setEnabled(false);
        ratingBarValue.setEnabled(false);

    }
}