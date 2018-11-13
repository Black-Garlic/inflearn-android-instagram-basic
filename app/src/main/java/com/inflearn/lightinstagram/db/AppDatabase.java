package com.inflearn.lightinstagram.db;

import com.inflearn.lightinstagram.data.entity.Feed;
import com.inflearn.lightinstagram.data.entity.Like;
import com.inflearn.lightinstagram.data.entity.Reply;
import com.inflearn.lightinstagram.data.entity.User;
import com.inflearn.lightinstagram.db.dao.FeedDao;
import com.inflearn.lightinstagram.db.dao.UserDao;
import com.inflearn.lightinstagram.ui.base.BaseApplication;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Feed.class,
        Like.class,
        Reply.class,
        User.class}, version = 1)
@TypeConverters(DateTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FeedDao feedDao();

    public abstract UserDao userDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase get() {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    BaseApplication.get(), AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
