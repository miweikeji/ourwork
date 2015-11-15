package app.dialog;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.miwei.jzj_system.R;

public class UserHeadPopup extends PopupWindow implements OnClickListener{

	private Activity activity;
	private View popup;
	private ViewFlipper viewfipper;
	
	public UserHeadPopup(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		LayoutInflater layout = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		popup = layout.inflate(R.layout.user_head_change, null);
		RelativeLayout rl_usre_head_dismiss = (RelativeLayout) popup.findViewById(R.id.rl_usre_head_dismiss);
		Button btn_from_Camera = (Button) popup.findViewById(R.id.btn_from_Camera);
		Button btn_from_phone = (Button) popup.findViewById(R.id.btn_from_phone);
		Button btn_cancel_ = (Button) popup.findViewById(R.id.btn_cancel_);
		btn_from_phone.setOnClickListener(this);
		btn_from_Camera.setOnClickListener(this);
		rl_usre_head_dismiss.setOnClickListener(this);
		btn_cancel_.setOnClickListener(this);
		viewfipper = new ViewFlipper(activity);
		viewfipper.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));

		viewfipper.addView(popup);
		viewfipper.setFlipInterval(6000000);
		this.setContentView(viewfipper);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		ColorDrawable dw = new ColorDrawable(0x00000000);
		this.setBackgroundDrawable(dw);
		this.update();
	}
	
	@Override
	public void showAtLocation(View parent, int gravity, int x, int y) {
		super.showAtLocation(parent, gravity, x, y);
		viewfipper.startFlipping();
	}
	
	UserHeadPopupDelegate delegate;

	public interface UserHeadPopupDelegate {
		public void getUserHeadPopupSuccess(int type);
	}

	public UserHeadPopup setDelegate(UserHeadPopupDelegate delegate) {
		this.delegate = delegate;
		return this;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_usre_head_dismiss:
			
			break;
		case R.id.btn_from_phone:
			this.delegate.getUserHeadPopupSuccess(0);
			break;
		case R.id.btn_from_Camera:
			this.delegate.getUserHeadPopupSuccess(1);
			break;
		case R.id.btn_cancel_:
			
			break;

		default:
			break;
		}
		this.dismiss();
	}

}
