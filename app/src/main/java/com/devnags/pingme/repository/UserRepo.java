package com.devnags.pingme.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.devnags.pingme.adapters.model.PhoneNumber;
import com.devnags.pingme.adapters.model.PhoneOtp;
import com.devnags.pingme.adapters.model.Profiles;
import com.devnags.pingme.adapters.model.Status;
import com.devnags.pingme.adapters.model.Token;
import com.devnags.pingme.db.AppDatabase;
import com.devnags.pingme.db.dao.UserDao;
import com.devnags.pingme.db.entity.MainModel;
import com.devnags.pingme.network.ApiCallback;
import com.devnags.pingme.network.ApiClient;
import com.devnags.pingme.network.ApiInterface;
import com.devnags.pingme.network.ApiRespCallback;
import com.devnags.pingme.network.ApiTokenCallback;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepo {

    private static final String TAG = "UserRepo";
    private PhoneNumber mPhoneNumber;
    private Status mStatus;
    private AppDatabase db;
    private UserDao dao;
    private LiveData<List<Profiles>> profileList;
    private int flag = 0;


    public UserRepo(Application application){
        db = AppDatabase.getInstance(application);
        dao = db.getUserDao();
        profileList = dao.getProfileInfo();
    }


    ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);


    public void verifyPhoneNumber(PhoneNumber number, ProgressBar progressBar,ApiCallback callback){

        Call<Status> call = service.verifyPhone(number);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Log.d(TAG, "onResponse: "+call.request().url());
                Log.d(TAG, "onResponse: "+call.request().headers());
                Log.d(TAG, "onResponse: "+call.request().body());
                assert response.body() != null;
                mStatus = new Status();
                mStatus.setStatus(response.body().isStatus());
                progressBar.setVisibility(View.GONE);
                callback.onResponse(response.body().isStatus());
                Log.d(TAG, "onResponse: "+mStatus.isStatus());

            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Log.d(TAG, "onFailure: "+call.request().url());
                Log.d(TAG, "onFailure: "+t);
            }
        });
    }


    public void verifyOtp(PhoneOtp number, ProgressBar progressBar, ApiTokenCallback callback){

    Call<Token> call = service.verifyOtp(number);
    call.enqueue(new Callback<Token>() {
        @Override
        public void onResponse(Call<Token> call, Response<Token> response) {
            progressBar.setVisibility(View.GONE);
            if (response.body() != null){
                callback.onResponse(response.body().getToken());
                Log.d(TAG, "onResponse: "+response.body().getToken());
            }
        }

        @Override
        public void onFailure(Call<Token> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
            Log.d(TAG, "onFailure: "+t);
        }
    });


    }


    public void getInvites(String token, ApiRespCallback callback){

    Call<MainModel> call = service.getProfileList(token);
    call.enqueue(new Callback<MainModel>() {
        @Override
        public void onResponse(Call<MainModel> call, Response<MainModel> response) {
            assert response.body() != null;
            Log.d(TAG, "onResponse: "+token);
            Log.d(TAG, "onResponse: "+response.body().invites);

            callback.onResponse(response.body().getInvites().getProfiles());

        }

        @Override
        public void onFailure(Call<MainModel> call, Throwable t) {
            Log.d(TAG, "onFailure: "+t);
        }
    });


    }


    public int insertAll(List<Profiles> list) {

        new loadDb(db).execute(list);

    return flag;

    }


    private class loadDb extends AsyncTask<List<Profiles>,Void,Void> {


        public loadDb(AppDatabase db) {
            dao = db.getUserDao();
        }


        @Override
        protected Void doInBackground(List<Profiles>... lists) {
             dao.insertProfile(lists[0]);

            return null;
        }
    }
}
