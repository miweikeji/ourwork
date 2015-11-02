package app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import app.entity.Advertise;

/**
 * Created by Administrator on 2015/10/10.
 */
public class BannerFragment extends Fragment{

    private ImageView img_banner;
    private DisplayImageOptions option;
    private ImageLoader imageLoader;
    private List<Advertise> advert;
    private int postion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_banner,null);

        img_banner = (ImageView) layout.findViewById(R.id.img_banner);
        if(imageLoader!=null){
            imageLoader.displayImage(advert.get(postion).getImgurl(),img_banner,option);
        }
        return layout;
    }

    public void setUrl(List<Advertise> advert) {
        this.advert = advert;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public void setOption(DisplayImageOptions option) {
        this.option = option;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }
}
