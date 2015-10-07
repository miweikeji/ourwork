package app.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miweikeij.app.R;

/**
 * Created by Administrator on 2015/10/2.
 */
public class NavigationBar extends LinearLayout implements View.OnClickListener {

    private Activity activity;
    private RelativeLayout  rl_navigation_bar;
    private TextView tv_title;
    private TextView tv_center_title;
    private TextView tv_right_title;
    private ImageView img_back;
    private Context context;

    private RightOnClick rightOnClick;

    public interface RightOnClick{
        public void setRightOnClick();
    }

    public void setRightOnClick(RightOnClick rightOnClick){
        this.rightOnClick = rightOnClick;
    }
    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.navigation_bar, this);

        rl_navigation_bar = (RelativeLayout)layout.findViewById(R.id.rl_navigation_bar);
        tv_title = (TextView)layout.findViewById(R.id.tv_title);
        tv_center_title = (TextView)layout.findViewById(R.id.tv_center_title);
        tv_right_title = (TextView)layout.findViewById(R.id.tv_right_title);
        img_back = (ImageView)layout.findViewById(R.id.img_back);
        img_back.setOnClickListener(this);
        tv_right_title.setOnClickListener(this);
    }

    private void setTitle(String title){
        tv_title.setText(title);
    }

    private void setCenterTitle(String centerTitle){
        tv_center_title.setVisibility(View.VISIBLE);
        tv_title.setVisibility(View.INVISIBLE);
        tv_center_title.setText(centerTitle);
    }

    private void setRightTitle(String rightTitle){
        tv_right_title.setVisibility(View.VISIBLE);
        tv_right_title.setText(rightTitle);
    }


    private void setBarInVisibile(){
        rl_navigation_bar.setVisibility(View.GONE);
    }

    private void setContexts(Activity activity){
        this.activity = activity;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                activity.finish();
                break;
            case R.id.tv_right_title:
                rightOnClick.setRightOnClick();
                break;
        }
    }
}
