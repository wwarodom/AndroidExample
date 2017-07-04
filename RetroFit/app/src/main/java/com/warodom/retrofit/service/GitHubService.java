package com.warodom.retrofit.service;

import com.warodom.retrofit.model.GitHubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wwaro on 7/4/2017.
 */

public interface GitHubService {
    @GET("users/{user}")        // MUST end URL WITHOUT '/'
    Call<GitHubUser> loadUser(@Path("user") String user);
}

