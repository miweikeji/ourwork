package app.activity.mywork.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.miwei.jzj_system.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import app.activity.mywork.AnimateFirstDisplayListener4;
import app.activity.mywork.LocalAlbumPreActivity;
import app.adapter.typeadapter.SingleTypeAdapter;
import app.entity.ImageEntity;
import app.tools.ScreenUtil;
import app.utils.Constants;
import app.utils.Uihelper;

public class LocalAlbumPreAdapter extends SingleTypeAdapter<ImageEntity> implements OnCheckedChangeListener, OnClickListener {
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private AnimateFirstDisplayListener4 listener = new AnimateFirstDisplayListener4();
    private int selectedCount;
    private int maxImageCount;
    ImageView img_bg;
    CheckBox cb;
    TextView tv_num;
    private LayoutParams params;
    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    private Object[] mItems;
    private boolean isSelectedFull;
    private LocalAlbumPreActivity preActivity;

    public LocalAlbumPreAdapter(LocalAlbumPreActivity preActivity,
                                Collection<ImageEntity> elements, int maxImageCount,
                                int selectedCount, OnLongClickListener onLongClickListenerImp) {
        super(preActivity, R.layout.grid_album_pre_item);
        this.preActivity = preActivity;
        this.maxImageCount = maxImageCount;
        this.selectedCount = selectedCount;
        imageLoader = ImageLoader.getInstance();
        options = Uihelper.getCameraPhotoOptions();
        setItems(elements);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.media_thumb, R.id.media_cbx, R.id.img_bg, R.id.tv_num};
    }

    @Override
    protected void update(int position, final ImageEntity item) {
        params = (LayoutParams) imageView(0).getLayoutParams();
        params.width = (ScreenUtil.getScreenWidth(preActivity) - 20) / 3;
        params.height = (ScreenUtil.getScreenWidth(preActivity) - 20) / 3;
        imageView(0).setLayoutParams(params);
        displayImage(item.getPath(), imageView(0));
        if (selectedCount >= 9) {
            isSelectedFull = true;
        }
        cb = view(1);
        cb.setTag(position);
        cb.setOnCheckedChangeListener(this);
        cb.setOnClickListener(this);

        img_bg = view(2);
        tv_num = view(3);

        cb.setTag(R.id.tv_num, tv_num);
        if (item.isSelected()) {
            cb.setChecked(true);
            if (item.getNum() != 0 && item.getNum() != null) {
                map.put(position, Integer.valueOf(item.getNum()));
                tv_num.setText("" + item.getNum());
            }
//			img_bg.setVisibility(View.VISIBLE);
        } else {
            cb.setChecked(false);
            tv_num.setText("");
//			img_bg.setVisibility(View.GONE);
        }
    }

    protected void displayImage(String uri, ImageView img) {
        if (null != imageLoader) {
            imageLoader.displayImage(uri, img, options, listener);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        final int position = (Integer) buttonView.getTag();
        TextView num = (TextView) buttonView.getTag(R.id.tv_num);
        final ImageEntity entity = getItem(position);
        if (isChecked) {
//			img_bg.setVisibility(View.VISIBLE);
            if (isSelectedFull && !entity.isSelected()) {
                buttonView.setChecked(false);
                Uihelper.showToast(preActivity, "所选照片超过9张。");
                return;
            }
            if (!entity.isSelected()) {
//				Animation anim=AnimationUtils.loadAnimation(preActivity,R.anim.photo_checked);				
//				buttonView.startAnimation(anim);				
                entity.setSelected(true);
                selectedCount++;
                if (selectedCount >= maxImageCount) {
                    isSelectedFull = true;
                }
                num.setText("" + selectedCount);
                entity.setNum(selectedCount);
                mItems = getAllItems();
                new Thread() {

                    @Override
                    public void run() {
                        super.run();
                        Uihelper.getImagesWH(preActivity, entity);
                        Message message = new Message();
                        message.what = 1;
                        message.obj = true;
                    }
                }.start();
                mItems[position] = entity;
                setItems(mItems);
                preActivity.updateSelectedImages(position, true);
                map.put(position, Integer.valueOf(selectedCount));
            }
        } else {
//			img_bg.setVisibility(View.GONE);
            if (entity.isSelected()) {
                entity.setSelected(false);
                selectedCount--;
                if (isSelectedFull) {
                    isSelectedFull = false;
                }
                preActivity.updateSelectedImages(position, false);
                map.remove(position);
                refreshNum(entity.getNum());
                entity.setNum(0);
                num.setText("");
            }
        }

    }

    /**
     * 更新选中图片时右上角的数字
     *
     * @param sNum 选中的第几张图片
     */
    private void refreshNum(int sNum) {
        int imageEntity_Position;
        int imageEntity_Num;
        mItems = getAllItems();
        if (map.size() > 0) {
            Iterator<Entry<Integer, Integer>> iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<Integer, Integer> e = (Entry<Integer, Integer>) iter.next();
                imageEntity_Position = e.getKey();
                imageEntity_Num = e.getValue();
                ImageEntity entity = (ImageEntity) mItems[imageEntity_Position];
                if (imageEntity_Num > sNum) {
                    entity.setNum(imageEntity_Num - 1);
                    mItems[imageEntity_Position] = entity;
                    e.setValue(imageEntity_Num - 1);
                } else {
                    entity.setNum(imageEntity_Num);
                    mItems[imageEntity_Position] = entity;
                }

            }

            setItems(mItems);
        }
    }

    public void setSelectedCount(int selectedCount) {
        this.selectedCount = selectedCount;
        if (selectedCount >= maxImageCount) {
            isSelectedFull = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.media_cbx:
                if (cb.isChecked()) {
                    img_bg.setVisibility(View.GONE);
                } else {
                    img_bg.setVisibility(View.VISIBLE);
                }

                break;
        }

    }
}
