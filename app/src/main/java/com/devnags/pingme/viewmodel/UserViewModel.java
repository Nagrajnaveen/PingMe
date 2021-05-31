package com.devnags.pingme.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.ProgressBar;

import com.devnags.pingme.adapters.model.Otp;
import com.devnags.pingme.adapters.model.PhoneNumber;
import com.devnags.pingme.adapters.model.PhoneOtp;
import com.devnags.pingme.adapters.model.Profiles;
import com.devnags.pingme.network.ApiCallback;
import com.devnags.pingme.network.ApiRespCallback;
import com.devnags.pingme.network.ApiTokenCallback;
import com.devnags.pingme.repository.UserRepo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class UserViewModel extends AndroidViewModel {

    private LiveData<List<Profiles>> profileList;
    UserRepo mRepo;
    private static final String TAG = "UserViewModel";


    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepo = new UserRepo(getApplication());
        profileList = mRepo.getProfileList();
    }


    public LiveData<List<Profiles>> getProfileList(){

        return mRepo.getProfileList();
    }


    public void getPhoneNumber(PhoneNumber number, ProgressBar progressBar, ApiCallback callback){
         mRepo.verifyPhoneNumber(number,progressBar,callback);

    }

    public void verifyOtp(PhoneOtp number, Otp otp,
                          ProgressBar progressBar, ApiTokenCallback callback){
        mRepo.verifyOtp(number,progressBar,callback);
    }

    public void getProfileList(String token, ApiRespCallback callback){
        mRepo.getInvites(token,callback);

    }

    public int insertAll(List<Profiles> list){
       return mRepo.insertAll(list);
    }





}
