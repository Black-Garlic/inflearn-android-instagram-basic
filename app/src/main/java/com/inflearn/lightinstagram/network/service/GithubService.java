package com.inflearn.lightinstagram.network.service;

import com.inflearn.lightinstagram.data.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubService {

    @GET("users")
    Call<List<User>> listUsers();
}