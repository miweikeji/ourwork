package app.activity.mywork;

import com.miweikeij.app.R;

import app.activity.BaseActivity;
import app.views.NavigationBar;

/**评价工匠
 * Created by tlt on 2015/10/26.
 */
public class ValueCraftActivity  extends BaseActivity{
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_valuecraft;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工匠评价");
    }
}
