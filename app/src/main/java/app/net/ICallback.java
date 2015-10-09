package app.net;

public interface ICallback<T> {
	
	public void onSucceed(T result);
	
	public void onFail(String error);

}
