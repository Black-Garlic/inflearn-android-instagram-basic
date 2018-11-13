package com.inflearn.lightinstagram.ui.feed;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inflearn.lightinstagram.R;
import com.inflearn.lightinstagram.data.entity.Feed;
import com.inflearn.lightinstagram.data.entity.User;
import com.inflearn.lightinstagram.ui.base.BaseViewHolder;

import androidx.annotation.NonNull;

public class FeedItemViewHolder extends BaseViewHolder<Feed> {

    private ImageView image;
    private ImageView imgAvatar;
    private TextView txtName;
    private TextView txtContent;

    private Feed feed;

    public FeedItemViewHolder(@NonNull ViewGroup parent) {
        super(getItemView(parent, R.layout.view_feed_item));
        findView(itemView);
    }

    private void findView(View view) {
        image = view.findViewById(R.id.image);
        imgAvatar = view.findViewById(R.id.img_avatar);
        txtName = view.findViewById(R.id.txt_name);
        txtContent = view.findViewById(R.id.txt_content);
    }

    @Override
    public void bind(final Feed feed) {
        this.feed = feed;

        setUser();
        setFeed();
    }

    private void setUser() {
        User user = feed.getUser();
        Glide.with(context)
                .load(user.getAvatarUrl())
                .into(imgAvatar);
        txtName.setText(user.getName());
    }

    private void setFeed() {
        Glide.with(context)
                .load(feed.getImageUrl())
                .into(image);
        txtContent.setText(feed.getText());
    }
}