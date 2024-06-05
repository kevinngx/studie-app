package com.example.stud_ie_app;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amitshekhar.DebugDB;

import com.example.stud_ie_app.DatabaseClasses.UserDatabase;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    //public UserDatabase mUserDatabase;
    public static final String BASE_URL = "https://wordsapiv1.p.rapidapi.com/words/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.wordText);
        System.out.println("This is our debug address: " + DebugDB.getAddressLog());

    }

    public void onButtonPress(View view) throws ExecutionException, InterruptedException {

//        TextView wordText = findViewById(R.id.wordText);
//        TextView sentenceView = findViewById(R.id.sentenceText);
//
//        WordApi wordApi = WordHelper.getWord();
//        OxfordApiSentence oxfordDictionaryApi = OxfordApiHelper.getSentence(wordApi.getWord());
//
//        while (oxfordDictionaryApi == null) {
//            wordApi = WordHelper.getWord();
//            oxfordDictionaryApi = OxfordApiHelper.getSentence(wordApi.getWord());
//        }
//
//        wordText.setText(wordApi.getWord());
//        String displaySentence = oxfordDictionaryApi.getSentencesList().get(0).text;
//        System.out.println(displaySentence);
//        sentenceView.setText(displaySentence);

//        Intent intent = new Intent(this, LevelSelectActivity.class);
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);

    }

}
