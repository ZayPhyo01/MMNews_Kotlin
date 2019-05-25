package com.padcmyanmar.padc7.mmnews.persistence.dao;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;

import java.util.List;

@Dao
public interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertComments(List<CommentVO> comments);

    @Delete
    int deleteComments(List<CommentVO> comments);

    @Delete
    int deleteComment(CommentVO comment);

    @Query("DELETE FROM comments WHERE news_id = :newsId")
    int deleteCommentsByNewsId(String newsId);

    @Query("SELECT * FROM comments WHERE news_id = :newsId")
    LiveData<List<CommentVO>> getCommentsByNewsId(String newsId);
}
