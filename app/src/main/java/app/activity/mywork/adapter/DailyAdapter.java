package app.activity.mywork.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import app.activity.GroupMembersActivity;
import app.adapter.AllAdapter;
import app.entity.DialyData;
import app.entity.GroupMembe;
import app.tools.StatusTools;
import app.utils.MobileOS;
import app.utils.Uihelper;
import app.views.CircleImageView;

/**
 * Created by Administrator on 2015/10/13.
 */
public class DailyAdapter extends AllAdapter {
    Activity activity;
    private List<DialyData> allList;
    List<String> imageList = new ArrayList<>();
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public DailyAdapter(Activity activity, List<DialyData> allList,
                        ImageLoader imageLoader, DisplayImageOptions options) {
        this.activity = activity;
        this.allList = allList;
        this.imageLoader = imageLoader;
        this.options = options;
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
            layout = activity.getLayoutInflater().inflate(R.layout.item_dairy, null);
            holder.gv_images = (GridView) layout.findViewById(R.id.gv_images);
            holder.tv_title = (TextView) layout.findViewById(R.id.tv_title);
            holder.tv_time = (TextView) layout.findViewById(R.id.tv_time);
            holder.tv_content = (TextView) layout.findViewById(R.id.tv_content);
            layout.setTag(holder);
        } else {
            holder = (ViewHolder) layout.getTag();
        }
        DialyData dialyData = allList.get(position);
        imageList.clear();
        if (dialyData != null) {
            addImageList(dialyData);
            holder.gv_images.setAdapter(new DailyPicAdapter(activity, imageList, imageLoader, options));
            holder.tv_time.setText(dialyData.getTime());
            holder.tv_content.setText(dialyData.getDecription());
            if (dialyData.getTitle_state() == 0) {
                StatusTools.setStatus(holder.tv_title, dialyData.getTitle_state() + "");
            } else {
                holder.tv_title.setText(dialyData.getTitle());
            }
        }

        return layout;
    }

    private void addImageList(DialyData dialyData) {
        if (!TextUtils.isEmpty(dialyData.getImage_1())) {
            imageList.add(dialyData.getImage_1());
        }
        if (!TextUtils.isEmpty(dialyData.getImage_2())) {
            imageList.add(dialyData.getImage_2());
        }
        if (!TextUtils.isEmpty(dialyData.getImage_3())) {
            imageList.add(dialyData.getImage_3());
        }
        if (!TextUtils.isEmpty(dialyData.getImage_4())) {
            imageList.add(dialyData.getImage_4());
        }
        if (!TextUtils.isEmpty(dialyData.getImage_5())) {
            imageList.add(dialyData.getImage_5());
        }
        if (!TextUtils.isEmpty(dialyData.getImage_6())) {
            imageList.add(dialyData.getImage_6());
        }
        if (!TextUtils.isEmpty(dialyData.getImage_7())) {
            imageList.add(dialyData.getImage_7());
        }
        if (!TextUtils.isEmpty(dialyData.getImage_8())) {
            imageList.add(dialyData.getImage_8());
        }
        if (!TextUtils.isEmpty(dialyData.getImage_9())) {
            imageList.add(dialyData.getImage_9());
        }
    }

    public class ViewHolder {
        GridView gv_images;
        TextView tv_title;
        TextView tv_time;
        TextView tv_content;
    }
}
