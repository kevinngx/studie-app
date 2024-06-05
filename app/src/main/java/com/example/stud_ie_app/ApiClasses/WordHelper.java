package com.example.stud_ie_app.ApiClasses;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WordHelper {

    public static final String BASE_URL = "https://wordsapiv1.p.rapidapi.com/words/";


    public static WordApi getWord() throws ExecutionException, InterruptedException {
        GetWordTask getWordTask = new GetWordTask();
        return getWordTask.execute().get();
    }

    private static class GetWordTask extends AsyncTask<Void, Void, WordApi> {

        @Override
        protected WordApi doInBackground(Void... voids) {

            WordApi result = null;

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            WordsApiClient service = retrofit.create(WordsApiClient.class);
            Call<WordApi> call = service.getRandomWord();

            try {
                result = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(WordApi wordApi) {
            System.out.println("In post execute");
            System.out.printf(wordApi.toString());
        }
    }

}
