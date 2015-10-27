package app.tools;

import java.util.ArrayList;
import java.util.List;

public class UIEventUpdate {
	
	
	public interface PositionKey{
		public static final int INVITATION_CASE = 1;
	}
	

	public static UIEventUpdate eventUpdate;
	
	public static UIEventUpdate getInstance(){
		if(eventUpdate==null){
			eventUpdate = new UIEventUpdate();
		}
		return eventUpdate;
	}
	
	public interface UIEventUpdateListener{
		public void setUIUpdate(int positionKey, Object object);
	}
	
	List<UIEventUpdateListener> mListener;
	
	public UIEventUpdate(){
		mListener = new ArrayList<UIEventUpdateListener>();
	}
	
	public void register(UIEventUpdateListener listener){
		if(!mListener.contains(listener)){
			mListener.add(listener);
		}
	}
	
	public void unregister(UIEventUpdateListener listener){
		if(mListener.contains(listener)){
			mListener.remove(listener);
		}
	}
	
	public void notifyUIUpdate(int positionKey,Object object){
		if(mListener==null){
			return ;
		}
		
		for (int i = 0; i < mListener.size(); i++) {
			UIEventUpdateListener updateListener = mListener.get(i);
			try {
				if(updateListener!=null){
					updateListener.setUIUpdate(positionKey, object);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
