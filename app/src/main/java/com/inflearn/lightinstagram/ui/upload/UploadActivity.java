package com.inflearn.lightinstagram.ui.upload;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.inflearn.lightinstagram.R;
import com.inflearn.lightinstagram.ui.base.BaseActivity;
import com.inflearn.lightinstagram.util.FileUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class UploadActivity extends BaseActivity {

    private final int CODE_GALLERY = 1;

    private FrameLayout boxImage;
    private ImageView imgUpload;
    private TextView txtImageGuide;
    private TextInputEditText inputText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findView();
        setBoxImage();
    }

    @Override
    protected void initializeToolbar(Toolbar toolbar) {
        super.initializeToolbar(toolbar);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(context, R.drawable.icon_back));
        toolbar.inflateMenu(R.menu.menu_upload);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_upload:
                        // Do Upload
                        finish();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_GALLERY:
                if (resultCode != Activity.RESULT_OK) break;
                Uri uri = data.getData();
                String path = FileUtil.getPath(context, uri);
                imgUpload.setImageBitmap(BitmapFactory.decodeFile(path));
                imgUpload.setVisibility(View.VISIBLE);
                txtImageGuide.setVisibility(View.GONE);
                break;
        }
    }

    private void findView() {
        boxImage = findViewById(R.id.box_image);
        imgUpload = findViewById(R.id.img_upload);
        txtImageGuide = findViewById(R.id.txt_image_guide);
        inputText = findViewById(R.id.input_text);
    }

    private void setBoxImage() {
        boxImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(FileUtil.MIME_IMAGE);
                startActivityForResult(intent, CODE_GALLERY);
            }
        });
    }
}