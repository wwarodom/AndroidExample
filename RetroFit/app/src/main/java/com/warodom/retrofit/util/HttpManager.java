package com.warodom.retrofit.util;

import android.content.Context;

import com.warodom.retrofit.service.GitHubService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wwaro on 7/4/2017.
 */

public class HttpManager {
    private static HttpManager instance;
 
    public static HttpManager getInstance() {
        if ( instance == null )
            instance = new HttpManager();
        return instance;
    }

    private Context mContext;
    private GitHubService service;

    private HttpManager() {
        mContext = Contextor.getInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")   // MUST end url with '/'
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GitHubService.class);
    }

    public GitHubService getService() {
        return service;
    }
}
