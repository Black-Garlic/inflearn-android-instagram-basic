package com.inflearn.lightinstagram.data.source;

import com.inflearn.lightinstagram.data.entity.User;
import com.inflearn.lightinstagram.network.RetrofitStore;
import com.inflearn.lightinstagram.network.service.GithubService;

import java.util.List;

import retrofit2.Callback;

public class GithubRemoteSource {

    private GithubService service = RetrofitStore.get().create(GithubService.class);

    public void get(Callback<List<User>> callback) {
        service.listUsers().enqueue(callback);
    }
}