package app.activity.mywork.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miwei.jzj_system.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.adapter.AllAdapter;
import app.entity.DialyData;
import app.entity.SystemMessage;
import app.tools.StatusTools;
import app.utils.Uihelper;
import app.views.SodukuGridView;

/**
 * Created by Administrator on 2015/10/13.
 */
public class SystemMessageAdapter extends AllAdapter {
    Activity activity;
    private List<SystemMessage> allList = new ArrayList<>();
    List<String> imageList = new ArrayList<>();
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public SystemMessageAdapter(Activity activity, List<SystemMessage> allList,
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
            holder.gv_images = (SodukuGridView) layout.findViewById(R.id.gv_images);
            holder.frame_state = (RelativeLayout) layout.findViewById(R.id.frame_state);
            holder.tv_title = (TextView) layout.findViewById(R.id.tv_title);
            holder.tv_state = (TextView) layout.findViewById(R.id.tv_state);
            holder.tv_time = (TextView) layout.findViewById(R.id.tv_time);
            holder.tv_content = (TextView) layout.findViewById(R.id.tv_content);
            layout.setTag(holder);
        } else {
            holder = (ViewHolder) layout.getTag();
        }
        SystemMessage systemMessage = allList.get(position);
        holder.frame_state.setVisibility(View.GONE);
        imageList.clear();
        if (systemMessage != null) {
            addImageList(systemMessage);
            holder.gv_images.setAdapter(new DailyPicAdapter(activity, imageList, imageLoader, options));
            holder.tv_time.setText(systemMessage.getTitle());
            holder.tv_content.setText(systemMessage.getContent());
            String addtime = systemMessage.getAddtime();
            if (!TextUtils.isEmpty(addtime)) {
                holder.tv_title.setText(Uihelper.timestampToDateStr(Double.parseDouble(addtime)));
            }
        }
        return layout;
    }

    private void addImageList(SystemMessage systemMessage) {
        if (!TextUtils.isEmpty(systemMessage.getImg1())) {
            imageList.add(systemMessage.getImg1());
        }
        if (!TextUtils.isEmpty(systemMessage.getImg2())) {
            imageList.add(systemMessage.getImg2());
        }
        if (!TextUtils.isEmpty(systemMessage.getImg3())) {
            imageList.add(systemMessage.getImg3());
        }
        if (!TextUtils.isEmpty(systemMessage.getImg4())) {
            imageList.add(systemMessage.getImg4());
        }
        if (!TextUtils.isEmpty(systemMessage.getImg5())) {
            imageList.add(systemMessage.getImg5());
        }
        if (!TextUtils.isEmpty(systemMessage.getImg6())) {
            imageList.add(systemMessage.getImg6());
        }
        if (!TextUtils.isEmpty(systemMessage.getImg7())) {
            imageList.add(systemMessage.getImg7());
        }
        if (!TextUtils.isEmpty(systemMessage.getImg8())) {
            imageList.add(systemMessage.getImg8());
        }
        if (!TextUtils.isEmpty(systemMessage.getImg9())) {
            imageList.add(systemMessage.getImg9());
        }
    }

    public class ViewHolder {
        SodukuGridView gv_images;
        TextView tv_title;
        TextView tv_time;
        TextView tv_content;
        TextView tv_state;
        RelativeLayout frame_state;
    }
}
