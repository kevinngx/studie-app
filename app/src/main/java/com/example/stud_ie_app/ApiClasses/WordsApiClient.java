package com.example.stud_ie_app.ApiClasses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface WordsApiClient {

    @Headers({"X-RapidAPI-Host:wordsapiv1.p.rapidapi.com", "X-RapidAPI-Key:6736cf0f27mshaef7fe6979d55adp105095jsn439ba4240c5a"})
//    @GET("?random=true")
    @GET("?random=true&lettersMax=7&letterpattern=^[a-zA-Z]+$")
    Call<WordApi> getRandomWord();

}
