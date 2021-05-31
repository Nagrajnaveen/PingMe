package com.devnags.pingme.adapters.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeProfiles {

    @SerializedName("likes_received_count")
    @Expose
    private Integer likesReceivedCount;


    public Integer getLikesReceivedCount() {
        return likesReceivedCount;
    }

    public void setLikesReceivedCount(Integer likesReceivedCount) {
        this.likesReceivedCount = likesReceivedCount;
    }
}
