package com.devnags.pingme.network;

import com.devnags.pingme.adapters.model.Profiles;

import java.util.List;

public interface ApiRespCallback {

    void onResponse(List<Profiles> list);
}
