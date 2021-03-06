package app.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * 额外操作监听器 ~
 * 
 * 
 */
public class ExtendOperationController {

	/**
	 * 操作KEY
	 */
	public static interface OperationKey {

		/** 登录成功 */
		public static final int LOGIN_SUCCESS = 1001;
		public static final int HouseInfo = 1002;
		public static final int EXIT_MYJOB = 1003;
	}

	/**
	 * 额外操作监听器
	 * 
	 * 
	 */
	public static interface ExtendOperationListener {
		public void excuteExtendOperation(int operationKey, Object data);
	}

	List<ExtendOperationListener> mListeners;
	static ExtendOperationController mControll;

	public ExtendOperationController() {
		mListeners = new LinkedList<ExtendOperationListener>();
	}

	public static ExtendOperationController getInstance() {
		if (mControll == null) {
			mControll = new ExtendOperationController();
		}
		return mControll;
	}

	public void registerExtendOperationListener(ExtendOperationListener listener) {

		if (!mListeners.contains(listener)) {
			mListeners.add(listener);
		}

	}

	public void unRegisterExtendOperationListener(ExtendOperationListener listener) {
		if (mListeners.contains(listener)) {
			mListeners.remove(listener);
		}

	}

	/**
	 * 通知执行操作
	 * 
	 * @param
	 */
	public void doNotificationExtendOperation(int operationKey, Object object) {
		if (mListeners == null)
			return;

		for (int i = 0; i < mListeners.size(); i++) {
			ExtendOperationListener listener = mListeners.get(i);
			try {
				if (listener != null) {
					listener.excuteExtendOperation(operationKey, object);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
