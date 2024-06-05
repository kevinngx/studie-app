package com.example.stud_ie_app.ApiClasses;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OxfordApiHelper {

    public static final String BASE_URL = "https://od-api.oxforddictionaries.com/api/v1/";

    public static ArrayList<String> getSynonyms(String word) throws ExecutionException, InterruptedException {
        GetSynonymTask getSynonymTask = new GetSynonymTask();
        OxfordApiSynonym oxfordApiSynonym = getSynonymTask.execute(word).get();
        ArrayList<String> synonyms = oxfordApiSynonym.getSynonymsList();
        Collections.shuffle(synonyms);
        return synonyms;
    }

    private static class GetSynonymTask extends  AsyncTask<String, Void, OxfordApiSynonym> {

        @Override
        protected OxfordApiSynonym doInBackground(String... strings) {
            OxfordApiSynonym result = null;

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            System.out.println("RETROFIT BULT");

            OxfordDictionaryClient service = retrofit.create(OxfordDictionaryClient.class);
            Call<OxfordApiSynonym> call = service.getSynonyms(strings[0]);

            try {
                result = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;

        }

    }


    public static String getSentence(String word) throws ExecutionException, InterruptedException {
        GetSentenceTask getSentenceTask = new OxfordApiHelper.GetSentenceTask();
        OxfordApiSentence oxfordApiSentence = getSentenceTask.execute(word).get();
        ArrayList<OxfordApiSentence.SentenceResult.lexicalEntries.Sentence> sentences = oxfordApiSentence.getSentencesList();
        Collections.shuffle(sentences);
        String sentence = sentences.get(0).text;
        System.out.println("Sentence pulled = " + sentence);
        return sentence;
    }

    public static ArrayList<String> getSentenceList(String word) throws ExecutionException, InterruptedException {
        GetSentenceTask getSentenceTask = new OxfordApiHelper.GetSentenceTask();
        OxfordApiSentence oxfordApiSentence = getSentenceTask.execute(word).get();
        ArrayList<OxfordApiSentence.SentenceResult.lexicalEntries.Sentence> sentences = oxfordApiSentence.getSentencesList();
        Collections.shuffle(sentences);
        ArrayList<String> sentenceList = new ArrayList<>();
        for (int i = 0; i < sentences.size(); i++) {
            sentenceList.add(sentences.get(i).text);
        }
//        String sentence = sentences.get(0).text;
//        System.out.println("Sentence pulled = " + sentence);
        return sentenceList;
    }

    private static class GetSentenceTask extends AsyncTask<String, Void, OxfordApiSentence> {

        @Override
        protected OxfordApiSentence doInBackground(String... strings) {
            OxfordApiSentence result = null;

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            OxfordDictionaryClient service = retrofit.create(OxfordDictionaryClient.class);
            Call<OxfordApiSentence> call = service.getSentence(strings[0]);

            try {
                result = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(OxfordApiSentence oxfordApiSentence) {

        }
    }

}
