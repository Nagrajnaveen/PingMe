package com.devnags.pingme.db.entity;

public class MainModel {


    public Invites invites;
    public Likes likes;


    public Invites getInvites() {
        return invites;
    }

    public void setInvites(Invites invites) {
        this.invites = invites;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }
}
