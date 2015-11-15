package app.tools;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miwei.jzj_system.R;

/**
 * Created by Administrator on 2015/11/14.
 */
public class Footools {

    public static void addFoot(PullToRefreshListView list,Activity activity,View inflate ){

        list.getRefreshableView().addFooterView(inflate);
    }

    public static void removeFoot(PullToRefreshListView list,Activity activity,View inflate ){
//        View inflate = activity.getLayoutInflater().inflate(R.layout.footview, null);
//        TextView video_item_label = (TextView) inflate.findViewById(R.id.video_item_label);
        list.getRefreshableView().removeFooterView(inflate);
    }


}
