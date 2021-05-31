package com.devnags.pingme.db.entity;

import com.devnags.pingme.adapters.model.Profiles;

import java.util.List;

public class Invites {


//    @TypeConverters(DataConverter.class)

    private List<Profiles> profiles ;

    private int totalPages;



    public List<Profiles> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profiles> profiles) {
        this.profiles = profiles;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
