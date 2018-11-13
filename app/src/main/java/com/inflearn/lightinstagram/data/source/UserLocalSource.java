package com.inflearn.lightinstagram.data.source;

import com.inflearn.lightinstagram.data.entity.User;
import com.inflearn.lightinstagram.db.AppDatabase;
import com.inflearn.lightinstagram.db.dao.UserDao;

import java.util.Date;

public class UserLocalSource {

    private AppDatabase db = AppDatabase.get();
    private UserDao dao = db.userDao();

    public User getMaster() {
        return dao.getMaster();
    }

    public User getUser(long userId) {
        return dao.getUser(userId);
    }

    public void insert(User user) {
        Date date = new Date();
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        dao.insert(user);
    }
}