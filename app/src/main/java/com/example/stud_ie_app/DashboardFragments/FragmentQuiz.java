package com.example.stud_ie_app.DashboardFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stud_ie_app.DatabaseClasses.ImageBank;
import com.example.stud_ie_app.DatabaseClasses.SessionData;
import com.example.stud_ie_app.QuestionBank;
import com.example.stud_ie_app.R;

import java.util.ArrayList;

public class FragmentQuiz extends Fragment {

    View view;

    // Ranking Titles
    TextView graduateTitle;
    TextView seniorTitle;
    TextView managerTitle;
    TextView executiveTitle;

    // Level Images
    ImageView transportImage;
    ImageView beachImage;
    ImageView sportsImage;
    ImageView jobsImage;
    ImageView weatherImage;
    ImageView natureImage;
    ImageView musicImage;
    ImageView exerciseImage;
    ImageView politicsImage;
    ImageView astronomyImage;

    // Level Titles
    TextView transportTitle;
    TextView beachTitle;
    TextView circusTitle;
    TextView jobsTitle;
    TextView weatherTitle;
    TextView natureTitle;
    TextView musicTitle;
    TextView exerciseTitle;
    TextView politicsTitle;
    TextView astronomyTitle;

    // Level Cards
    CardView transportCard;
    CardView beachCard;
    CardView circusCard;
    CardView jobsCard;
    CardView weatherCard;
    CardView natureCard;
    CardView musicCard;
    CardView exerciseCard;
    CardView politicsCard;
    CardView astronomyCard;

    ArrayList<ImageView> images = new ArrayList<>();
    ArrayList<TextView> titles = new ArrayList<>();
    ArrayList<CardView> cards = new ArrayList<>();

    public FragmentQuiz() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.quiz_fragment, container, false);
        findViews();
        deactivateAll();
        activateUnlockedLevels();
        return view;
    }

    private void activateUnlockedLevels() {
        unlockLevel(0);
        unlockLevel(1);

        if (SessionData.currentUser.getScore() >= 1000) {
            graduateTitle.setText("Graduate");
            unlockLevel(2);
            unlockLevel(3);
        }

        if (SessionData.currentUser.getScore() >= 5000) {
            seniorTitle.setText("Senior");
            unlockLevel(4);
            unlockLevel(5);
        }

        if (SessionData.currentUser.getScore() >= 10000) {
            managerTitle.setText("Manager");
            unlockLevel(6);
            unlockLevel(7);
        }

        if (SessionData.currentUser.getScore() >= 25000) {
            executiveTitle.setText("Executive");
            unlockLevel(8);
            unlockLevel(9);
        }


    }

    private void unlockLevel(int levelNumber) {
        titles.get(levelNumber).setText(QuestionBank.categories[levelNumber]);
        images.get(levelNumber).setImageResource(ImageBank.levelImages[levelNumber]);
        cards.get(levelNumber).setEnabled(true);
        cards.get(levelNumber).setCardBackgroundColor(Color.WHITE);

    }


    private void findViews() {
        // Find Ranking Titles
        graduateTitle = (TextView) view.findViewById(R.id.heading_graduate);
        seniorTitle = (TextView) view.findViewById(R.id.heading_senior);
        managerTitle = (TextView) view.findViewById(R.id.heading_manager);
        executiveTitle = (TextView) view.findViewById(R.id.heading_executive);

        // Find ImageViews
        transportImage = (ImageView) view.findViewById(R.id.level_transport_image);
        beachImage = (ImageView) view.findViewById(R.id.level_beach_image);
        sportsImage = (ImageView) view.findViewById(R.id.level_circus_image);
        jobsImage = (ImageView) view.findViewById(R.id.level_jobs_image);
        weatherImage = (ImageView) view.findViewById(R.id.level_weather_image);
        natureImage = (ImageView) view.findViewById(R.id.level_nature_image);
        musicImage = (ImageView) view.findViewById(R.id.level_music_image);
        exerciseImage = (ImageView) view.findViewById(R.id.level_exercise_image);
        politicsImage = (ImageView) view.findViewById(R.id.level_politics_image);
        astronomyImage = (ImageView) view.findViewById(R.id.level_astronomy_image);
        images.add(transportImage);
        images.add(beachImage);
        images.add(sportsImage);
        images.add(jobsImage);
        images.add(weatherImage);
        images.add(natureImage);
        images.add(musicImage);
        images.add(exerciseImage);
        images.add(politicsImage);
        images.add(astronomyImage);

        // Find TextViews
        transportTitle = (TextView) view.findViewById(R.id.level_transport_title);
        beachTitle = (TextView) view.findViewById(R.id.level_beach_title);
        circusTitle = (TextView) view.findViewById(R.id.level_circus_title);
        jobsTitle = (TextView) view.findViewById(R.id.level_jobs_title);
        weatherTitle = (TextView) view.findViewById(R.id.level_weather_title);
        natureTitle = (TextView) view.findViewById(R.id.level_nature_title);
        musicTitle = (TextView) view.findViewById(R.id.level_music_title);
        exerciseTitle = (TextView) view.findViewById(R.id.level_exercise_title);
        politicsTitle = (TextView) view.findViewById(R.id.level_politics_title);
        astronomyTitle = (TextView) view.findViewById(R.id.level_astronomy_title);
        titles.add(transportTitle);
        titles.add(beachTitle);
        titles.add(circusTitle);
        titles.add(jobsTitle);
        titles.add(weatherTitle);
        titles.add(natureTitle);
        titles.add(musicTitle);
        titles.add(exerciseTitle);
        titles.add(politicsTitle);
        titles.add(astronomyTitle);

        // Find CardViews
        transportCard = (CardView) view.findViewById(R.id.level_transport);
        beachCard = (CardView) view.findViewById(R.id.level_beach);
        circusCard = (CardView) view.findViewById(R.id.level_circus);
        jobsCard = (CardView) view.findViewById(R.id.level_jobs);
        weatherCard = (CardView) view.findViewById(R.id.level_weather);
        natureCard = (CardView) view.findViewById(R.id.level_nature);
        musicCard = (CardView) view.findViewById(R.id.level_music);
        exerciseCard = (CardView) view.findViewById(R.id.level_exercise);
        politicsCard = (CardView) view.findViewById(R.id.level_politics);
        astronomyCard = (CardView) view.findViewById(R.id.level_astronomy);
        cards.add(transportCard);
        cards.add(beachCard);
        cards.add(circusCard);
        cards.add(jobsCard);
        cards.add(weatherCard);
        cards.add(natureCard);
        cards.add(musicCard);
        cards.add(exerciseCard);
        cards.add(politicsCard);
        cards.add(astronomyCard);

    }

    public void deactivateAll() {
        // Lock all cards
        for (int i = 0; i < cards.size(); i++) {
            images.get(i).setImageResource(R.drawable.level_locked);
            titles.get(i).setText("Level Locked");
            cards.get(i).setEnabled(false);
            cards.get(i).setCardBackgroundColor(Color.parseColor("#ABB4BE"));
        }

        graduateTitle.setText("Earn 1,000 points to unlock Graduate Levels");
        seniorTitle.setText("Earn 5,000 points to unlock Senior Levels");
        managerTitle.setText("Earn 10,000 points to unlock Manager Levels");
        executiveTitle.setText("Earn 25,000 points to unlock Executive Levels");
    }
}
