package app.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.mywork.LocalAlbumPreActivity;
import app.activity.mywork.adapter.FooterSelectedPicAdapter;
import app.entity.ImageEntity;
import app.utils.Constants;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/25.
 */
public class PublishDairyActivity extends BaseActivity {
    private EditText etDairy;
    private TextView tvAccount;
    private LinearLayout lin_pics_h;
    private HorizontalScrollView scroll_h;
    private FooterSelectedPicAdapter picAdapter;
    private ArrayList<ImageEntity> selectedAlbumImages = new ArrayList<ImageEntity>();
    private int imageTotalCount = Constants.MAX_SELECT_PHOTO_NUM;

    @Override
    public void obtainData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {

                case 1:
                    if (data == null) {

                        return;
                    }
                    List<ImageEntity> imageList = (ArrayList<ImageEntity>) data
                            .getSerializableExtra("selectedAlbumImages");
                    if (imageList != null && imageList.size() != 0) {
                        addImages(imageList);
                    } else {
                        selectedAlbumImages.clear();
                    }
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void initUI() {

        lin_pics_h = (LinearLayout) findViewById(R.id.lin_pics_h);
        scroll_h = (HorizontalScrollView) findViewById(R.id.scroll_h);
        // 图片
        picAdapter = new FooterSelectedPicAdapter(PublishDairyActivity.this,
                lin_pics_h, onAddPicClick, onDelClick);

        tvAccount = (TextView) findViewById(R.id.tv_account);
        etDairy = (EditText) findViewById(R.id.ed_dairy);
        etDairy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence string, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String text = s.toString();
                if (text.length() > 140) {
                    Uihelper.showToast(mActivity, "最多输入140个字");
                } else {
                    tvAccount.setText(text.length() + "");
                }

            }
        });

    }

    private void addImages(List<ImageEntity> imageList) {
        if (imageList != null && imageList.size() > 0) {
            selectedAlbumImages.clear();
            selectedAlbumImages.addAll(imageList);
            picAdapter.addItems(imageList);
            scroll_h.post(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    scroll_h.fullScroll(ScrollView.FOCUS_RIGHT);
                }
            });
        }

    }

    private void refreshSelectedAlbumImages(int position) {
        ImageEntity current_imageEntity;
        ImageEntity imageEntity;
        current_imageEntity = selectedAlbumImages.get(position);
        for (int i = 0; i < selectedAlbumImages.size(); i++) {
            imageEntity = selectedAlbumImages.get(i);
            if (current_imageEntity.getNum() < imageEntity.getNum()) {
                imageEntity.setNum(imageEntity.getNum() - 1);
                selectedAlbumImages.set(i, imageEntity);
            }
        }
    }

    // 删除图片
    View.OnClickListener onDelClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int position = (Integer) v.getTag();
            refreshSelectedAlbumImages(position);
            picAdapter.removeItem(position);
            selectedAlbumImages.remove(position);
            picAdapter.notifyDataSetChanged();
        }
    };

    // 点击添加图片的按钮
    View.OnClickListener onAddPicClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            selectAllPhoto();

        }
    };

    private void selectAllPhoto() {
        Intent intent = new Intent(mActivity,
                LocalAlbumPreActivity.class);
        intent.putExtra("maxImageCount", imageTotalCount - 1
                - selectedAlbumImages.size() + 1);
        intent.putExtra("selectedAlbumImages", selectedAlbumImages);
        startActivityForResult(intent, 1);

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_publishdairy;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("发布装修日记");

    }
}
