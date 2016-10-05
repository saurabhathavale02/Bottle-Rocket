package com.example.saurabh.storedatadisplay.model;

import com.example.saurabh.storedatadisplay.model.pojo.StoreData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;


/**
 * Created by saurabh on 10/02/16.
 */

public class StoreDataApiImpl
{
   // OkHttpClient.Builder okClientBuilder;
    private interface PostService
    {
        @GET("stores.json")
        Observable<StoreData> getPostsList();
    }
    OkHttpClient client = getOkHttpClient().build();
    Observable<StoreData> postsObservable= new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl("http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/")
            .build().create(PostService.class).getPostsList();


    public Observable<StoreData> getPostsObservable()
    {
        return postsObservable;
    }

    public OkHttpClient.Builder getOkHttpClient()
    {
        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();
        okClientBuilder.connectTimeout(10, TimeUnit.SECONDS);
        okClientBuilder.readTimeout(10, TimeUnit.SECONDS);
        return okClientBuilder;
    }
}
