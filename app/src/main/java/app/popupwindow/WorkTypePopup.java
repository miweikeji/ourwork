package app.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;

import com.miwei.jzj_system.R;

import app.adapter.WorkTypeAdater;
import app.tools.ScreenUtil;

/**
 * Created by Administrator on 2015/10/10.
 */
public class WorkTypePopup extends PopupWindow{

    private String [] items;
    private Activity mContext;
    private OnPopupWindowClickListener listener;
    public WorkTypePopup(Activity context, String [] items){
        super(context);
        mContext = context;
        this.items = items;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_work_type, null);
        setContentView(popupView);

        //设置宽度,若没有设置宽度为LayoutParams.WRAP_CONTENT
        int width = ScreenUtil.instance(mContext).getScreenWidth();
//        int w = ScreenUtil.instance(mContext).dip2px(30);
        setWidth(width);
        setHeight(LayoutParams.WRAP_CONTENT);

        //设置动画，也可以不设置。不设置则是显示默认的
//        setAnimationStyle(R.style.popupwindow_animation);

        //这里很重要，不设置这个ListView得不到相应
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);

        ListView list = (ListView)popupView.findViewById(R.id.list_work_type);
        WorkTypeAdater adater = new WorkTypeAdater(mContext,items);
        list.setAdapter(adater);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkTypePopup.this.dismiss();
                if(listener!=null){
                    listener.onPopupWindowItemClick(position);
                }
            }
        });
    }

    public void setOnPopupWindowClickListener(OnPopupWindowClickListener listener){
        this.listener = listener;
    }

    public interface OnPopupWindowClickListener{
        /**
         * 当点击PopupWindow的ListView 的item的时候调用此方法，用回调方法的好处就是降低耦合性
         * @param position 位置
         */
        void onPopupWindowItemClick(int position);
    }
}
