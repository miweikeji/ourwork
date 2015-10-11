package app.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

import app.tools.MyLog;

/**
 * Created by Administrator on 2015/10/11.
 */
public class AutoLoadListView extends ListView implements AbsListView.OnScrollListener {

    private AutoLoadingListener mListViewListener;
    private boolean isOver;
    private AutoLoadListView foot;
    public AutoLoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public AutoLoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AutoLoadListView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        super.setOnScrollListener(this);
        AutoLoadListView foot = new AutoLoadListView(context);
    }


    /**
     * 鏁版嵁鍔犺浇鎴愬姛澶辫触鍚庤皟鐢�
     *
     * @param enable
     */
    public void setIsOver(boolean isOver){
        if(isOver){
            if(foot!=null){
                removeFooterView(foot);
            }
        }
        this.isOver = isOver;
    }
    /**
     * 鍚庡彴鏁版嵁鍏ㄩ儴鍔犺浇瀹�
     *
     * @param enable
     */
    public void setHasData(boolean isOver){
        if(!isOver){
            if(foot!=null){
                removeFooterView(foot);
            }
        }
        this.isOver = isOver;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem+visibleItemCount>=totalItemCount-5&&isOver){
            MyLog.e("", "===============onScroll============" + isOver);
            addFooterView(foot);
            if (mListViewListener != null) {
                mListViewListener.onLoadMore();
            }
            isOver = false;
        }
    }

    public void setAutoLoadListener(AutoLoadingListener l) {
        mListViewListener = l;
    }

    public interface AutoLoadingListener {
        public void onLoadMore();
    }
}
