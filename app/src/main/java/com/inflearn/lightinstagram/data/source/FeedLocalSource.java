package com.inflearn.lightinstagram.data.source;

import com.inflearn.lightinstagram.data.entity.Feed;
import com.inflearn.lightinstagram.db.AppDatabase;
import com.inflearn.lightinstagram.db.dao.FeedDao;

import java.util.Date;
import java.util.List;

public class FeedLocalSource {

    private AppDatabase db = AppDatabase.get();
    private FeedDao dao = db.feedDao();

    public List<Feed> getAll() {
        return dao.getAll();
    }

    public void insert(Feed feed) {
        Date date = new Date();
        feed.setCreatedAt(date);
        feed.setUpdatedAt(date);
        dao.insert(feed);
    }
}