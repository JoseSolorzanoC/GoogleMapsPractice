package com.uteq.appmoviles.googlemapspractice.Services;

import com.google.gson.JsonObject;
import com.uteq.appmoviles.googlemapspractice.Models.Country.CountryAll;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GeoService {

    @GET("info/all.json")
    Call<CountryAll> getAll();

    @GET("info/{code}.json")
    Call<JsonObject> getOne(@Path("code") String code);

}
