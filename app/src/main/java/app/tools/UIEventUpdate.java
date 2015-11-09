package app.tools;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import app.entity.Time;

public class UIEventUpdate {
	
	
	public interface PositionKey{
		public static final int INVITATION_CASE = 1;
		public static final int INVITATION_SERVERTIME=2;
		public static final int INVIATION_CHANGEtASKS=3;
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

	public interface DataListener{
		public void setSendDate(int positionKey, List<Time> list,String type);
	}
	
	List<UIEventUpdateListener> mListener;
	List<DataListener> dataListener;

	public UIEventUpdate(){
		mListener = new ArrayList<UIEventUpdateListener>();
		dataListener  = new ArrayList<DataListener>() ;

	}


	
	public void register(UIEventUpdateListener listener){
		if(!mListener.contains(listener)){
			mListener.add(listener);
		}
	}

	public void registerData(DataListener listener){
		if(!dataListener.contains(listener)){
			dataListener.add(listener);
		}
	}
	
	public void unregister(UIEventUpdateListener listener){
		if(mListener.contains(listener)){
			mListener.remove(listener);
		}
	}

	public void unregisterData(DataListener listener){
		if(!dataListener.contains(listener)){
			dataListener.remove(listener);
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

	public void customSendData(int positionKey,List<Time> list,String type){
		if(mListener==null){
			return ;
		}

		for (int i = 0; i < mListener.size(); i++) {
			DataListener updateListener = dataListener.get(i);
			try {
				if(updateListener!=null){
					updateListener.setSendDate(positionKey, list, type);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
