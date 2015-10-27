package app.tools;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2015/10/27.
 */
public class ImageLoadTools {

    public static void  displayImage(String url,ImageView view,DisplayImageOptions options){

        ImageLoader.getInstance().displayImage(url,view,options);
    }
}
