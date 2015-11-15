package app.tools;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import app.entity.Ctime;
import app.entity.Time;

public class UIEventUpdate {
	
	
	public interface PositionKey{
		public static final int INVITATION_CASE = 1;
		public static final int INVITATION_SERVERTIME=2;
		public static final int INVIATION_CHANGEtASKS=3;
		public static final int INVIATION_SERVERCTIME=11;
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

	public interface DataCtimListener{
		void setSendCtimDate(int positionKey, List<Ctime> list,String type);
	}
	
	List<UIEventUpdateListener> mListener;
	List<DataListener> dataListener;
	List<DataCtimListener> dataCtimListener;

	public UIEventUpdate(){
		mListener = new ArrayList<UIEventUpdateListener>();
		dataListener  = new ArrayList<DataListener>() ;
		dataCtimListener = new ArrayList<DataCtimListener>();
	}


	public void ctimregister(DataCtimListener listener){
		if(!dataCtimListener.contains(listener)){
			dataCtimListener.add(listener);
		}
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

	public void ctimUnregister(DataCtimListener listener){
		if(!dataCtimListener.contains(listener)){
			dataCtimListener.remove(listener);
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
		if(dataListener==null){
			return ;
		}

		for (int i = 0; i < dataListener.size(); i++) {
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

	public void customSendCtimData(int positionKey,List<Ctime> list,String type){
		if(dataCtimListener==null){
			return ;
		}

		for (int i = 0; i < dataCtimListener.size(); i++) {
			DataCtimListener dataCtimListener = this.dataCtimListener.get(i);
			try {
				if(dataCtimListener!=null){
					dataCtimListener.setSendCtimDate(positionKey, list, type);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
