package com.devnags.pingme.db;

import com.devnags.pingme.adapters.model.GeneralInformation;
import com.devnags.pingme.adapters.model.Photo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;


public class DataConverter implements Serializable {
    @TypeConverter
    public String fromGenInfo(GeneralInformation information){

        if (information == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<GeneralInformation>(){}.getType();
        String json = gson.toJson(information,type);
        return json;
    }

    @TypeConverter
    public GeneralInformation toGenInfo(String information){
        if (information == null){return null;}

        Gson gson = new Gson();
        Type type = new TypeToken<GeneralInformation>(){}.getType();
        GeneralInformation infoList = gson.fromJson(information,type);
        return infoList;
    }

    @TypeConverter
    public String fromPhotosList(List<Photo> photos){

        if (photos == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Photo>>(){}.getType();
        String json = gson.toJson(photos,type);
        return json;
    }

    @TypeConverter
    public List<Photo> toPhotosList(String photos){
        if (photos == null){return null;}

        Gson gson = new Gson();
        Type type = new TypeToken<List<Photo>>(){}.getType();
        List<Photo> profilesList = gson.fromJson(photos,type);
        return profilesList;
    }


}
