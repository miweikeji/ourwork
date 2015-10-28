package app.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.miweikeij.app.R;

import java.util.List;

import app.adapter.typeadapter.SingleTypeAdapter;
import app.entity.Meta;
import app.entity.ProtectRecord;
import app.utils.Uihelper;

/**
 * Created by tlt on 2015/10/18.
 */
public class ProtectRecordAdapter extends SingleTypeAdapter<ProtectRecord> {


    public ProtectRecordAdapter(Context context, List<ProtectRecord> items) {
        super(context, R.layout.item_protect_record);
        setItems(items);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_title, R.id.tv_money, R.id.tv_time};
    }

    @Override
    protected void update(int position, ProtectRecord item) {

        String type = item.getType();
        if (!TextUtils.isEmpty(type)) {
            switch (type) {
                case "0"://充值
                    textView(0).setText("保证金充值");
                    break;
                case "1": //退款
                    textView(0).setText("保证金退款");
                    break;
                case "2":// 工资
                    textView(0).setText("工资");
                    break;
                default:
                    break;
            }
        }
        textView(1).setText("￥" + item.getMoney());
        if (!TextUtils.isEmpty(item.getAddtime())) {
            String time = Uihelper.timestampToDateStr(Double.parseDouble(item.getAddtime()));
            textView(2).setText(time);
        }

    }
}
