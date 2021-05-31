package com.devnags.pingme.db.entity;

import com.devnags.pingme.adapters.model.LikeProfiles;

import java.util.List;


public class Likes {


//    @TypeConverters(DataConverter.class)
    private List<LikeProfiles> profiles ;

    private int likes_received_count;

//    @NonNull
    public List<LikeProfiles> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<LikeProfiles> profiles) {
        this.profiles = profiles;
    }

    public int getLikes_received_count() {
        return likes_received_count;
    }

    public void setLikes_received_count(int likes_received_count) {
        this.likes_received_count = likes_received_count;
    }



}
