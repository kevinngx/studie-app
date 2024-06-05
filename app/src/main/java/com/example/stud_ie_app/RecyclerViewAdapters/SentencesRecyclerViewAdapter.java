package com.example.stud_ie_app.RecyclerViewAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stud_ie_app.R;

import java.util.List;

public class SentencesRecyclerViewAdapter extends RecyclerView.Adapter<SentencesRecyclerViewAdapter.SentenceViewHolder> {

    Context mContext;
    List<String> mData;

    public SentencesRecyclerViewAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }


    @NonNull
    @Override
    public SentenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView;
        myView = LayoutInflater.from(mContext).inflate(R.layout.sentence_card, parent, false);
        SentenceViewHolder viewHoler = new SentenceViewHolder(myView);
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull SentenceViewHolder sentenceViewHolder, int position) {
        sentenceViewHolder.sentence.setText(mData.get(position));
        sentenceViewHolder.sentenceNumber.setText(Integer.toString((position + 1)));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class SentenceViewHolder extends RecyclerView.ViewHolder {

        TextView sentence;
        TextView sentenceNumber;

        public SentenceViewHolder(@NonNull View itemView) {
            super(itemView);

            sentence = (TextView) itemView.findViewById(R.id.popup_sentence);
            sentenceNumber = (TextView) itemView.findViewById(R.id.popup_sentenceNumber);

        }

    }
}
