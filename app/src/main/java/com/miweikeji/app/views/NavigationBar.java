package com.miweikeji.app.views;

import android.widget.LinearLayout;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.miweikeij.app.R;
/**
 * Created by Administrator on 2015/10/2.
 */
public class NavigationBar extends LinearLayout {

    private Context context;
    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.navigation_bar, this);

    }
}
