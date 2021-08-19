package com.uteq.appmoviles.googlemapspractice.Services;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GeoService {

    @GET("info/all.json")
    Call<JsonObject> getAll();

    @GET("info/{code}.json")
    Call<JsonObject> getOne(@Path("code") String code);

}
