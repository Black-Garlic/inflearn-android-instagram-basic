package com.inflearn.lightinstagram.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Feed.class,
                parentColumns = "id",
                childColumns = "feed_id",
                onDelete = CASCADE),
        @ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "user_id")
})
public class Like {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "feed_id")
    @SerializedName("feed_id")
    private long feedId;

    @ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    private long userId;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    private Date createdAt;

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    private Date updatedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFeedId() {
        return feedId;
    }

    public void setFeedId(long feedId) {
        this.feedId = feedId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}