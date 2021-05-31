package com.devnags.pingme.db;

import android.content.Context;

import com.devnags.pingme.adapters.model.Profiles;
import com.devnags.pingme.db.dao.UserDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Profiles.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "User_DB";
    public static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract UserDao getUserDao();

    public static AppDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            synchronized (LOCK)
            {
                instance = Room.databaseBuilder(context,AppDatabase.class,DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return instance;
    }

}

