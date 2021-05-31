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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.devnags.pingme.R;
import com.devnags.pingme.adapters.model.PhoneNumber;
import com.devnags.pingme.adapters.model.Status;
import com.devnags.pingme.databinding.FragmentPhoneVerifyBinding;
import com.devnags.pingme.network.ApiCallback;
import com.devnags.pingme.viewmodel.UserViewModel;


public class PhoneVerifyFragment extends Fragment  {

    private NavController navController;
    private FragmentPhoneVerifyBinding mBinding;
    private UserViewModel mViewModel;
    private static final String TAG = "PhoneVerifyFragment";
    private PhoneNumber mNumber;
    private Status mStatus;
    private Bundle mBundle;
    private SharedPreferences mPref;
    private Context mContext;
    private String mToken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentPhoneVerifyBinding.inflate(getLayoutInflater(), container, false);
        mContext = getActivity();
        assert mContext!= null;
        mPref = getActivity().getSharedPreferences(getString(R.string.my_preference),
                Context.MODE_PRIVATE);
        mToken = mPref.getString(getString(R.string.token),"");

        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        Log.d(TAG, "onViewCreated: "+mToken);
        if (!(mToken.equals(""))) {
            navController.navigate(R.id.action_phoneVerifyFragment_to_homeFragment);
        }
        mViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        mBundle = new Bundle();
        mBinding.rectangle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNumber= new PhoneNumber("+91"+mBinding.phoneTextView.getText().toString());
                verifyPhone(mNumber, mBinding.progressBar, new ApiCallback() {
                    @Override
                    public void onResponse(boolean status) {
                        if (status){
                            mBundle.putString("number", mNumber.getNumber());
                            navController.navigate(R.id.action_phoneVerifyFragment_to_otpVerifyFragment2,mBundle);
                        }else {
                            Toast.makeText(getActivity(), ""+status, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }

    public void verifyPhone(PhoneNumber number, ProgressBar progressBar, ApiCallback callback) {
        Log.d(TAG, "verifyPhone: clicked.........");
        mViewModel.getPhoneNumber(number, progressBar,callback);
    }
}