package com.devnags.pingme.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.devnags.pingme.R;
import com.devnags.pingme.adapters.model.Otp;
import com.devnags.pingme.adapters.model.PhoneOtp;
import com.devnags.pingme.databinding.FragmentOtpVerifyBinding;
import com.devnags.pingme.network.ApiTokenCallback;
import com.devnags.pingme.viewmodel.UserViewModel;

public class OtpVerifyFragment extends Fragment {

    private FragmentOtpVerifyBinding mBinding;
    private UserViewModel mViewModel;
    private PhoneOtp mNumberOtp;
    private String mSafeArgsPhoneNum;
    private Otp mOtp;
    private SharedPreferences mSharedPreferences;
    private Context mContext;
    private NavController mNavController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentOtpVerifyBinding.inflate(getLayoutInflater(),container, false);
        assert getArguments() != null;
        mSafeArgsPhoneNum = getArguments().getString("number");
        mContext = getActivity();
        assert mContext != null;
        mSharedPreferences = mContext.getSharedPreferences(getString(R.string.my_preference),Context.MODE_PRIVATE);
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNavController = Navigation.findNavController(view);
        mViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        mOtp = new Otp(mBinding.otpTextView.getText().toString());
        mNumberOtp = new PhoneOtp(mSafeArgsPhoneNum,mBinding.otpTextView.getText().toString());

        startTimer();
        mBinding.rectangle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               verifyOtp(mNumberOtp, mOtp,
                       mBinding.progressBar2, new ApiTokenCallback() {
                           @Override
                           public void onResponse(String token) {
                            setmSharedPreferences(token,mNumberOtp.getNumber());
                            mNavController.navigate(R.id.action_otpVerifyFragment2_to_homeFragment);
                           }
                       });

            }
        });


    }


    public void startTimer(){
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                mBinding.timerTextView.setText("00: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mBinding.resendOtpText.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    void verifyOtp(PhoneOtp mSafeArgsPhoneNum, Otp otp,
                   ProgressBar progressBar2,
                   ApiTokenCallback apiTokenCallback){
        mViewModel.verifyOtp(mSafeArgsPhoneNum,otp,progressBar2, apiTokenCallback);
    }

    void setmSharedPreferences(String token, String number){
        SharedPreferences.Editor pref= mSharedPreferences.edit();
        pref.putString(getString(R.string.token),token);
        pref.putString(getString(R.string.phone_num), number);
        pref.apply();
    }

}