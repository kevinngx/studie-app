package com.example.stud_ie_app.ApiClasses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface OxfordDictionaryClient {



    @Headers({"app_id:fbd620e7", "app_key:ec6b3656d9e50818105ca7561de4b245"})
    @GET("entries/en/{word}/sentences")
    Call<OxfordApiSentence> getSentence(@Path("word") String word);

    @Headers({"app_id:fbd620e7", "app_key:ec6b3656d9e50818105ca7561de4b245"})
    @GET("entries/en/{word}/synonyms")
    Call<OxfordApiSynonym> getSynonyms(@Path("word") String word);


}
