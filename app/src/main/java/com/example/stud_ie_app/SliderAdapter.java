package com.example.stud_ie_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater mLayoutInflater;

    public SliderAdapter(Context context) {

        this.context = context;
    }

    public int[] slideImages = {
            R.drawable.onboarding_welcome,
            R.drawable.onboarding_learn,
            R.drawable.onboarding_grow,
            R.drawable.onboarding_compete,
            R.drawable.onboarding_start
    };

    public String[] slideHeadings = {
            "Welcome",
            "Learn",
            "Grow",
            "Compete",
            "Get Started"
    };

    public String[] slideDescriptions = {
            // Welcome
            "Hi there! I'm Adam Chew, the CEO of Stud.ie. Welcome to our quiz app! " +
                    "\n\nWe have introduced this app as a new way to assess our new employees and " +
                    "their potential for promotion.",

            // Learn
            "Here you will be tested on your knowledge of english grammar. " +
                    "\n\nEach question of the quiz will present you with a displaySentence with a missing word. " +
                    "It is your job to find the missing word",

            // Grow
            "Get these questions right to build to increases your experience. " +
                    "Gain enough experience points, and we may even consider you for promotion!" +
                    "\n\nAs you climb the corporate ladder, you will unlock even more levels to test" +
                    "your skills!",

            // Compete
            "We are a global company so there will be other employees around the world also" +
                    "pushing to climb the corporate ladder here at Stud.ie. " +
                    "\n\nTrack your performance and compete against other wordsmiths around the world.",

            // Get Started!
            "So what are you waiting for? Press the button below to get started!"
    };


    @Override
    public int getCount() {
        return slideHeadings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImage = (ImageView) view.findViewById(R.id.slideImage);
        TextView slideHeading = (TextView) view.findViewById(R.id.slideHeading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slideDescription);

        slideImage.setImageResource(slideImages[position]);
        slideHeading.setText(slideHeadings[position]);
        slideDescription.setText(slideDescriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
