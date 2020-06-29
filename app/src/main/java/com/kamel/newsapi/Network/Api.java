package com.kamel.newsapi.Network;

import com.kamel.newsapi.ui.ListNews.model.ResponseNews;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("top-headlines")
    Single<ResponseNews> getNews(@Query("apiKey") String apiKey,
                                 @Query("country") String country,
                                 @Query("category") String category);
}
