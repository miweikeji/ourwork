package app.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.miweikeij.app.R;

import java.util.List;

import app.adapter.typeadapter.SingleTypeAdapter;
import app.entity.ProtectRecord;
import app.entity.Score;
import app.utils.Uihelper;

/**
 * Created by tlt on 2015/10/18.
 */
public class ScoreAdapter extends SingleTypeAdapter<Score> {


    public ScoreAdapter(Context context, List<Score> items) {
        super(context, R.layout.item_protect_record);
        setItems(items);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_title, R.id.tv_money, R.id.tv_time};
    }

    @Override
    protected void update(int position, Score item) {

        String type = item.getType();
        if (!TextUtils.isEmpty(type)) {
            //type 0签到 1申请接单2工匠认证3注册4发布找搭档5现场日志6意见反馈
            switch (type) {
                case "0"://签到
                    textView(0).setText("签到");
                    break;
                case "1": //申请接单
                    textView(0).setText("申请接单");
                    break;
                case "2":// 工匠认证
                    textView(0).setText("工匠认证");
                    break;
                case "3":// 工匠认证
                    textView(0).setText("工匠认证");
                    break;
                case "4":// 发布找搭档
                    textView(0).setText("发布找搭档");
                    break;
                case "5":// 现场日志
                    textView(0).setText("现场日志");
                    break;
                case "6":// 意见反馈
                    textView(0).setText("意见反馈");
                    break;
                default:
                    break;
            }
        }
        textView(1).setText(item.getScore()+"积分");
        if (!TextUtils.isEmpty(item.getAddtime())) {
            String time = Uihelper.timestampToDateStr(Double.parseDouble(item.getAddtime()));
            textView(2).setText(time);
        }

    }
}
