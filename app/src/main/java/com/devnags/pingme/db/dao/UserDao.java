package com.devnags.pingme.db.dao;


import com.devnags.pingme.adapters.model.Profiles;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {

    //Profile op
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insertProfile(List<Profiles> profiles);

   @Query("Select * from Profiles")
   LiveData<List<Profiles>> getProfileInfo();



}
