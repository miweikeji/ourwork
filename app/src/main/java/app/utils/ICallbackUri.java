package app.utils;

public interface ICallbackUri<T> {
	public void onSucceedUri(T result);

	public void onFailUri(String error);

}
