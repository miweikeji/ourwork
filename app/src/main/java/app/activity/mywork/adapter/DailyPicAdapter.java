package app.activity.mywork.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import app.adapter.AllAdapter;
import app.entity.DialyData;
import app.utils.MobileOS;
import app.utils.Uihelper;

/**
 * Created by Administrator on 2015/10/13.
 */
public class DailyPicAdapter extends AllAdapter {
    private final int columnWidth;
    Activity activity;
    private List<String> allList;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public DailyPicAdapter(Activity activity, List<String> imageList,
                           ImageLoader imageLoader, DisplayImageOptions options) {
        this.activity = activity;
        this.allList = imageList;
        this.imageLoader = imageLoader;
        this.options = options;
        // 计算宽度
        columnWidth = (int) ((MobileOS.getScreenWidth(activity) - activity
                .getResources().getDimension(R.dimen.dairy_padding_h) * 4) / 3)
                - Uihelper.dip2px(activity, 5);
    }

    @Override
    public int getCount() {
        return allList == null ? 0 : allList.size();
    }

    @Override
    public View getView(int position, View layout, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (layout == null) {
            holder = new ViewHolder();
            layout = activity.getLayoutInflater().inflate(R.layout.item_dairy_pic, null);
            holder.iv_pic = (ImageView) layout.findViewById(R.id.iv_pic);
            layout.setTag(holder);
        } else {
            holder = (ViewHolder) layout.getTag();
        }
        ViewGroup.LayoutParams lp = holder.iv_pic.getLayoutParams();
        lp.height = columnWidth;
        lp.width = columnWidth;
        holder.iv_pic.setLayoutParams(lp);
        imageLoader.displayImage("https://gd1.alicdn.com/bao/uploaded/i1/TB161JKJVXXXXb2XVXXXXXXXXXX_!!0-item_pic.jpg", holder.iv_pic, options);
        return layout;
    }

    public class ViewHolder {
        ImageView iv_pic;
    }
}
