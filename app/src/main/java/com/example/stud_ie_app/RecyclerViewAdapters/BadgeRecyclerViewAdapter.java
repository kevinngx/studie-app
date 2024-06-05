package com.example.stud_ie_app.RecyclerViewAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stud_ie_app.Badges;
import com.example.stud_ie_app.DatabaseClasses.ImageBank;
import com.example.stud_ie_app.DatabaseClasses.SessionData;
import com.example.stud_ie_app.R;

import java.util.List;

public class BadgeRecyclerViewAdapter extends RecyclerView.Adapter<BadgeRecyclerViewAdapter.BadgeViewHolder> {
    Context mContext;
    private List<Badges> mData;

    public BadgeRecyclerViewAdapter(Context context, List<Badges> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public BadgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView;
        myView = LayoutInflater.from(mContext).inflate(R.layout.badge_simple, parent, false);
        final BadgeViewHolder viewHolder = new BadgeViewHolder(myView);

        viewHolder.badgeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Card clicked: " + mData.get(viewHolder.getAdapterPosition()).getName());
                SessionData.displayBadge(mContext, mData.get(viewHolder.getAdapterPosition()).getBadgeID());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BadgeViewHolder badgeViewHolder, int position) {
        badgeViewHolder.badgeImage.setImageResource(ImageBank.badges[mData.get(position).getIcon()]);
        badgeViewHolder.badgeTitle.setText(mData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class BadgeViewHolder extends RecyclerView.ViewHolder {

        TextView badgeTitle;
        ImageView badgeImage;
        CardView badgeCard;

        public BadgeViewHolder(@NonNull View itemView) {
            super(itemView);

            badgeTitle = itemView.findViewById(R.id.badge_title);
            badgeImage = itemView.findViewById(R.id.badge_image);
            badgeCard = itemView.findViewById(R.id.badge_card);
        }
    }
}
