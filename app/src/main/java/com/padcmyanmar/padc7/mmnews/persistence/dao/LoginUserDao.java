package com.padcmyanmar.padc7.mmnews.persistence.dao;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;

@Dao
public interface LoginUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long inserLoginUser(LoginUserVO loginUser);

    @Query("SELECT * FROM login_user")
    LoginUserVO getLoginUser();

    @Query("DELETE FROM login_user")
    void deleteLoginUser();
}
