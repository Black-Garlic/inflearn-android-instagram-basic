package com.inflearn.lightinstagram.ui.upload;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.inflearn.lightinstagram.R;
import com.inflearn.lightinstagram.data.entity.Feed;
import com.inflearn.lightinstagram.data.entity.User;
import com.inflearn.lightinstagram.data.source.FeedLocalSource;
import com.inflearn.lightinstagram.data.source.UserLocalSource;
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
    private String imagePath;

    private FeedLocalSource feedLocalSource = new FeedLocalSource();
    private UserLocalSource userLocalSource = new UserLocalSource();

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
                        upload();
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
                imagePath = FileUtil.getPath(context, uri);
                imgUpload.setImageBitmap(BitmapFactory.decodeFile(imagePath));
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

    private void upload() {
        User user = userLocalSource.getMaster();
        String text = inputText.getText().toString();

        if (!validate(user, text, imagePath)) return;

        Feed feed = new Feed(user.getId(), inputText.getText().toString(), imagePath);
        feedLocalSource.insert(feed);
        finish();
    }

    private boolean validate(User user, String text, String imagePath) {
        if (user == null) {
            Toast.makeText(context, R.string.upload_empty_user, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(text)) {
            Toast.makeText(context, R.string.upload_empty_text, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (imagePath == null) {
            Toast.makeText(context, R.string.upload_empty_image, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}