package com.padcmyanmar.padc7.mmnews.data.vos;


import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "comments",
        foreignKeys = @ForeignKey(entity = NewsVO.class,
                parentColumns = "news_id",
                childColumns = "news_id"), indices = {@Index(value = {"comment_id"}, unique = true)})
public class CommentVO {

    @PrimaryKey(autoGenerate = true)
    private long commentIdPk;

    @SerializedName("comment-id")
    @ColumnInfo(name = "comment_id")
    private String commentId;

    @ColumnInfo(name = "comment")
    @SerializedName("comment")
    private String comment;

    @ColumnInfo(name = "comment_date")
    @SerializedName("comment-date")
    private String commentDate;

    @Embedded
    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    @ColumnInfo(name = "news_id")
    private String newsId;

    public String getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public void setActedUser(ActedUserVO actedUser) {
        this.actedUser = actedUser;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public long getCommentIdPk() {
        return commentIdPk;
    }

    public void setCommentIdPk(long commentIdPk) {
        this.commentIdPk = commentIdPk;
    }
}
