package com.devnags.pingme.adapters.model;

import com.devnags.pingme.db.DataConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
public class Profiles {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("general_information")
    @Expose
    @TypeConverters(DataConverter.class)
    private GeneralInformation generalInformation;
    @SerializedName("approved_time")
    @Expose
    private Double approvedTime;
    @SerializedName("photos")
    @Expose
    @TypeConverters(DataConverter.class)
    private List<Photo> photos = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GeneralInformation getGeneralInformation() {
        return generalInformation;
    }

    public void setGeneralInformation(GeneralInformation generalInformation) {
        this.generalInformation = generalInformation;
    }

    public Double getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(Double approvedTime) {
        this.approvedTime = approvedTime;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }


    public Profiles(int id, GeneralInformation generalInformation, Double approvedTime, List<Photo> photos) {
        this.id = id;
        this.generalInformation = generalInformation;
        this.approvedTime = approvedTime;
        this.photos = photos;
    }
}
