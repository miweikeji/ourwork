package app.views;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.miwei.jzj_system.R;

import app.utils.Uihelper;

/**
 * 
 * @author Tuliangtan
 *
 */
public class ProgressDialogView {
	private static View view = null;
	private static Dialog dialog = null;


	public static Dialog create(Context context) {
		view = LayoutInflater.from(context).inflate(R.layout.progressdialog_layout, null);
		dialog = new Dialog(context, R.style.loading_dialog);
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
		lp.width = Uihelper.px2dip(context,80); // 宽度
		lp.height = Uihelper.px2dip(context, 80); // 高度
		lp.alpha = 0.6f; // 透明度
		dialogWindow.setAttributes(lp);
		dialogWindow.setContentView(view, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return dialog;
	}


}
