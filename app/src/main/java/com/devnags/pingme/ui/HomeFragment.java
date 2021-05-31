package com.devnags.pingme.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devnags.pingme.R;
import com.devnags.pingme.adapters.model.Profiles;
import com.devnags.pingme.bindigAdapters.BindingAdapter;
import com.devnags.pingme.databinding.FragmentHomeBinding;
import com.devnags.pingme.network.ApiRespCallback;
import com.devnags.pingme.viewmodel.UserViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class HomeFragment extends Fragment {

    private UserViewModel mViewModel;
    private SharedPreferences mPref;
    private FragmentHomeBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = FragmentHomeBinding.inflate(getLayoutInflater(),container,false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        mPref = getActivity().getSharedPreferences(getString(R.string.my_preference),
                Context.MODE_PRIVATE);
        String token = mPref.getString(getString(R.string.token),"");
        mViewModel.getProfileList(token, new ApiRespCallback() {
            @Override
            public void onResponse(List<Profiles> list) {
                insertData(list);
            }
        });

        mViewModel.getProfileList().observe(getViewLifecycleOwner(), new Observer<List<Profiles>>() {
            @Override
            public void onChanged(List<Profiles> profiles) {
                mBinding.meena23.setText(profiles.get(0).getGeneralInformation().getFirstName());
                Picasso.get().load(profiles.get(0).getPhotos().get(0).getPhoto())
            .into(mBinding.rectangle1);
            }
        });

    }

    private int insertData(List<Profiles> list) {
       return mViewModel.insertAll(list);
    }


}