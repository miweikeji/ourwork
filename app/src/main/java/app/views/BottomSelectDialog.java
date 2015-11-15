package app.views;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.miwei.jzj_system.R;

import app.utils.Config;


/**
 * 底部弹出的对话框选项，包含一个默认取消
 * 
 * @author TuLiangtan
 * 
 */
public class BottomSelectDialog extends Activity {
	static Context mContext;

	public enum PopUpDialogPosition {
		bottom, middle
	}

	/**
	 * 创建底部弹出对话框
	 * 
	 * @author TuLiangtan
	 * @date 2015-4-8
	 * @param onClickListener
	 *            点击事件
	 * @return
	 */

	public static Dialog create(final Context context, PopUpDialogPosition popUpDialogPosition, OnClickListener onClickListener) {
		mContext = context;
		View view = LayoutInflater.from(context).inflate(R.layout.bottom_select_dialog, null);
		Button btn_write = (Button) view.findViewById(R.id.btn_write);
		Button btn_check = (Button) view.findViewById(R.id.btn_check);
		Button btn_value = (Button) view.findViewById(R.id.btn_value);
		ImageView iv_downarrow = (ImageView) view.findViewById(R.id.iv_downarrow);
		btn_write.setOnClickListener(onClickListener);
		btn_check.setOnClickListener(onClickListener);
		btn_value.setOnClickListener(onClickListener);
		iv_downarrow.setOnClickListener(onClickListener);
		// 设置宽度
		LinearLayout llOp = (LinearLayout) view.findViewById(R.id.llOp);
		LinearLayout.LayoutParams para = (LinearLayout.LayoutParams) llOp.getLayoutParams();

		llOp.setLayoutParams(para);

		// 弹出框属性
		final Dialog dialog = new Dialog(context, R.style.bottom_dialog);
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		if (popUpDialogPosition.equals(PopUpDialogPosition.bottom)) {
			// 底部弹出，进出效果
			dialogWindow.setGravity(Gravity.BOTTOM);
			// 进出效果
			dialogWindow.setWindowAnimations(R.style.BottomAlertAni);
			para.width = Config.WIDTH;
		} else if (popUpDialogPosition.equals(PopUpDialogPosition.middle)) {
			dialogWindow.setGravity(Gravity.CENTER);
		}

		lp.width = LayoutParams.WRAP_CONTENT; // 宽度
		lp.height = LayoutParams.WRAP_CONTENT; // 高度
		dialogWindow.setBackgroundDrawableResource(R.color.transparent);
		dialogWindow.setAttributes(lp);
		dialogWindow.setContentView(view, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		dialog.setCanceledOnTouchOutside(true);
		return dialog;
	}

}
