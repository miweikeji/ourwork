package app.activity.mywork;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miweikeij.app.R;

import app.activity.BaseActivity;
import app.activity.mywork.adapter.LocalAlbumPreAdapter;
import app.entity.ImageEntity;
import app.utils.Constants;
import app.views.NavigationBar;


public class LocalAlbumPreActivity extends BaseActivity {
    private GridView mGridView;
    private LocalAlbumPreAdapter mAdapter;
    private ArrayList<ImageEntity> albumImages, selectedAlbumImages;
    private int maxImagesCount;
    private boolean isAlbumsInto;
    private int flag;
    private String hintFromat;

    @Override
    public void obtainData() {
        Intent intent = getIntent();
        maxImagesCount = intent.getIntExtra("maxImageCount",
                Constants.MAX_SELECT_PHOTO_NUM);// 还可选的图片数量
        selectedAlbumImages = (ArrayList<ImageEntity>) intent.getSerializableExtra("selectedAlbumImages");
        isAlbumsInto = intent.getBooleanExtra("isAlbumsInto", false);
        if (isAlbumsInto == true) {
            albumImages = (ArrayList<ImageEntity>) intent.getSerializableExtra("images");//所有图片
        } else {
            find();
        }
        int selectedCount = 0;
        selectedCount = Constants.MAX_SELECT_PHOTO_NUM - maxImagesCount;//已经选的图片数
        mAdapter = new LocalAlbumPreAdapter(this, albumImages, Constants.MAX_SELECT_PHOTO_NUM,
                selectedCount, null);
        mGridView.setAdapter(mAdapter);
    }

    @Override
    public void initUI() {
        flag = getIntent().getFlags();
        mGridView = (GridView) findViewById(R.id.media_in_folder_gv);
        hintFromat = "最多选取9张";
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_local_album_pre;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("选择照片");
        mBar.setRightTitle("完成");
        mBar.setRightOnClick(new NavigationBar.RightOnClick() {
            @Override
            public void setRightOnClick() {
                send();
            }
        });

    }

    private static final String[] PROJECTION = {
            MediaStore.Images.Media.DISPLAY_NAME, // abs
            MediaStore.Images.Media.DATA // path
    };

    private List<ImageEntity> find() {
        albumImages = new ArrayList<ImageEntity>();
        Cursor cursor = this.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, PROJECTION, null,
                null, MediaStore.Images.Media.DATE_TAKEN + " desc");
        int p = 0;
        while (cursor.moveToNext()) {
            String columnPath = cursor.getString(cursor
                    .getColumnIndex(MediaColumns.DATA));// 图片路径
            String columnImageName = cursor.getString(cursor
                    .getColumnIndex(MediaColumns.DISPLAY_NAME));// 图片名字
            columnPath = "file://" + columnPath;
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(columnImageName);
            imageEntity.setPath(columnPath);
            imageEntity.setPosition(p++);

            if (selectedAlbumImages != null) {
                for (int i = 0; i < selectedAlbumImages.size(); i++) {
                    ImageEntity _imageEntity = selectedAlbumImages.get(i);
                    if (columnPath.equals(_imageEntity.getPath())) {
                        imageEntity.setNum(_imageEntity.getNum());
                        imageEntity.setSelected(_imageEntity.isSelected());
                        imageEntity.setHeight(_imageEntity.getHeight());
                        imageEntity.setWidth(_imageEntity.getWidth());
                    }
                }
            }

            albumImages.add(imageEntity);
        }
        return albumImages;
    }


    @Override
    public void onBackPressed() {
        send();
    }


    /**
     * 发送选中的图片
     */
    public void send() {
        ArrayList<String> list = new ArrayList<String>();
        for (ImageEntity entity : albumImages) {
            if (entity.isSelected()) {
                list.add(entity.getPath());
            }
        }
        Intent intent = new Intent();
        intent.putExtra("imagesPath", list);
        intent.putExtra("selectedAlbumImages", selectedAlbumImages);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    /**
     * 更新已经选择的图片
     *
     * @param position
     * @param isAdd
     */
    public void updateSelectedImages(int position, boolean isAdd) {
        if (selectedAlbumImages == null) {
            selectedAlbumImages = new ArrayList<ImageEntity>();
        }
        ImageEntity entity;
        ImageEntity imageEntity;
        ImageEntity _imageEntity;
        entity = albumImages.get(position);
        if (isAdd) {//增加图片
            selectedAlbumImages.add(entity);
        } else {//取消图片
            int len = selectedAlbumImages.size();
            for (int i = 0; i < len; i++) {
                imageEntity = selectedAlbumImages.get(i);
                if (imageEntity.getPath() != null && imageEntity.getPath().equals(entity.getPath())) {
                    for (int j = 0; j < len; j++) {
                        _imageEntity = selectedAlbumImages.get(j);
                        if (_imageEntity.getNum() != null && imageEntity.getNum() < _imageEntity.getNum()) {
                            _imageEntity.setNum(_imageEntity.getNum() - 1);
                            selectedAlbumImages.set(j, _imageEntity);
                        }
                    }
                    selectedAlbumImages.remove(i);
                    break;
                }
            }
        }
    }

}
