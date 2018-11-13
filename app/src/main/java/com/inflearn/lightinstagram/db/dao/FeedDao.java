package com.inflearn.lightinstagram.db.dao;

import com.inflearn.lightinstagram.data.entity.Feed;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FeedDao {
    @Query("SELECT * FROM feed ORDER BY updated_at DESC")
    List<Feed> getAll();

    @Insert
    void insert(Feed feed);
}
