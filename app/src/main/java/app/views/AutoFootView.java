package app.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.miwei.jzj_system.R;


public class AutoFootView extends LinearLayout{

	public AutoFootView(Context context) {
		super(context);
		init(context);
	}

	public AutoFootView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		// TODO Auto-generated method stub
		LinearLayout moreView = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.layout_auto_foot, null);
		addView(moreView);
		moreView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	}

}
