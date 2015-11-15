package app.activity.mywork.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.miwei.jzj_system.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import app.entity.ImageEntity;
import app.tools.ScreenUtil;
import app.utils.Uihelper;

public class FooterSelectedPicAdapter {
    List<ImageEntity> mImageList;
    List<String> mPathList;
    Context mContext;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    LinearLayout mParent;
    OnClickListener mOnAddBtnClick, mOnDelClick;
    boolean isShowAll;
    int maxWidth;

    public FooterSelectedPicAdapter(Context context, LinearLayout parent,
                                    OnClickListener onAddBtnClick, OnClickListener onDelClick) {
        mContext = context;
        mParent = parent;
        isShowAll = false;
        mImageList = new ArrayList<ImageEntity>();
        mPathList = new ArrayList<String>();
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new RoundedBitmapDisplayer(0)).build();
        mOnAddBtnClick = onAddBtnClick;// 添加图片
        mOnDelClick = onDelClick;// 删除图片
        maxWidth = ScreenUtil.instance(mContext).getScreenWidth()
                - Uihelper.dip2px(mContext, 40);
        appendAddPic();
    }

    public FooterSelectedPicAdapter(Context context, LinearLayout parent,
                                    OnClickListener onAddBtnClick, OnClickListener onDelClick,
                                    boolean isShowAll) {
        mContext = context;
        mParent = parent;
        this.isShowAll = isShowAll;
        mImageList = new ArrayList<ImageEntity>();
        mPathList = new ArrayList<String>();
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new RoundedBitmapDisplayer(0)).build();
        mOnAddBtnClick = onAddBtnClick;// 添加图片
        mOnDelClick = onDelClick;// 删除图片
        appendAddPic();
    }

    public ImageEntity getItem(int position) {
        // TODO Auto-generated method stub
        return mImageList.get(position);
    }

    private View getView(final int position) {
        ImageEntity imageEntity = mImageList.get(position);
        String uri = imageEntity.getPath();
        View itemView = LayoutInflater.from(mContext).inflate(
                R.layout.item_footer_selected_pic, null);// 添加图片按钮
        ImageView im_pic = (ImageView) itemView
                .findViewById(R.id.im_pic);
        View layout_main = itemView.findViewById(R.id.layout_main);
        View layout_del = itemView.findViewById(R.id.layout_del);
        // 计算宽高比
        int height = mContext.getResources().getDimensionPixelSize(
                R.dimen.footer_pic_height);
        int width = 0;
        if (imageEntity.getHeight() > 0 && imageEntity.getWidth() > 0) {
            width = (int) (imageEntity.getWidth() * height / imageEntity
                    .getHeight());
        }
        if (width > 0) {
            if (width > maxWidth) {
                width = maxWidth;
            }
            int layoutWidth = width + Uihelper.dip2px(mContext, 22);
            int layoutHeight = mContext.getResources().getDimensionPixelSize(
                    R.dimen.footer_pic_layout_height);
            LayoutParams lp = (LayoutParams) im_pic.getLayoutParams();
            lp.width = width;
            lp.height = mContext.getResources().getDimensionPixelSize(
                    R.dimen.footer_pic_height);
            im_pic.setLayoutParams(lp);
            LayoutParams lpMain = new LayoutParams(layoutWidth, layoutHeight);
            layout_main.setLayoutParams(lpMain);
            layout_del.setLayoutParams(lpMain);
        }
        ImageView im_del = (ImageView) itemView.findViewById(R.id.im_del);
        im_del.setTag(position);
        im_del.setOnClickListener(mOnDelClick);
        im_pic.setScaleType(ScaleType.FIT_CENTER);
        imageLoader.displayImage(uri, im_pic, options);
        return itemView;
    }

    private void appendAddPic() {
        View itemView = LayoutInflater.from(mContext).inflate(
                R.layout.item_footer_selected_pic, null);
        ImageView im_pic = (ImageView) itemView
                .findViewById(R.id.im_pic);
        ImageView im_del = (ImageView) itemView.findViewById(R.id.im_del);// 删除
        im_del.setVisibility(View.GONE);
        im_pic.setScaleType(ScaleType.FIT_XY);
        im_pic.setBackgroundResource(R.mipmap.icon_addimage);
        im_pic.setOnClickListener(mOnAddBtnClick);
        mParent.addView(itemView);
    }

    private void appendView(int position) {
        View view = getView(position);
        mParent.addView(view, mParent.getChildCount() - 1);
    }

    public void addItems(List<ImageEntity> imageList) {
        if (imageList != null && imageList.size() > 0) {

            if (mImageList == null) {
                mImageList = new ArrayList<ImageEntity>();
            }
            int startIndex = mImageList.size();
            mPathList.clear();
            mImageList.clear();
            for (ImageEntity image : imageList) {
                mPathList.add(image.getPath());
                mImageList.add(image);
            }
            for (int i = startIndex; i < mImageList.size(); i++) {
                appendView(i);
            }
            notifyDataSetChanged();
        }

    }


    public List<ImageEntity> getItems() {
        return mImageList;
    }

    public void removeItem(int position) {
        if (mImageList != null && mImageList.size() > position) {
            mImageList.remove(position);
            mPathList.remove(position);
            mParent.removeViewAt(position);
        }
    }

    public void notifyDataSetChanged() {
        mParent.removeAllViews();
        appendAddPic();
        for (int i = 0; i < mImageList.size(); i++) {
            appendView(i);
        }
    }

    public void clearList() {
        if (mImageList != null) {
            mImageList.clear();
            mParent.removeAllViews();
            appendAddPic();
        }
    }
}
