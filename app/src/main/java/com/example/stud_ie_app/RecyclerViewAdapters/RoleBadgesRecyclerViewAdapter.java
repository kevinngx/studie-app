package com.example.stud_ie_app.RecyclerViewAdapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stud_ie_app.Badges;
import com.example.stud_ie_app.DatabaseClasses.ImageBank;
import com.example.stud_ie_app.DatabaseClasses.SessionData;
import com.example.stud_ie_app.R;

import java.util.List;

public class RoleBadgesRecyclerViewAdapter extends RecyclerView.Adapter<RoleBadgesRecyclerViewAdapter.RoleBadgesViewHolder> {
    Context mContext;
    private List<Badges> mData;
    int selectedPosition=-1;

    public RoleBadgesRecyclerViewAdapter(Context context, List<Badges> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RoleBadgesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView;
        myView = LayoutInflater.from(mContext).inflate(R.layout.badge_simple, parent, false);
        final RoleBadgesViewHolder viewHolder = new RoleBadgesViewHolder(myView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoleBadgesViewHolder roleBadgesViewHolder, final int position) {
        roleBadgesViewHolder.badgeImage.setImageResource(ImageBank.badges[mData.get(position).getIcon()]);
        roleBadgesViewHolder.badgeTitle.setText(mData.get(position).getName());
        roleBadgesViewHolder.badgeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();
                SessionData.selectedRole = mData.get(position).getName();
            }
        });
        if (position == selectedPosition) {
            roleBadgesViewHolder.badgeCard.setBackgroundColor(Color.parseColor("#293D58"));
            roleBadgesViewHolder.badgeTitle.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            roleBadgesViewHolder.badgeCard.setBackgroundColor(Color.parseColor("#FFFFFF"));
            roleBadgesViewHolder.badgeTitle.setTextColor(Color.parseColor("#293D58"));
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class RoleBadgesViewHolder extends RecyclerView.ViewHolder {

        TextView badgeTitle;
        ImageView badgeImage;
        CardView badgeCard;

        public RoleBadgesViewHolder(@NonNull View itemView) {
            super(itemView);

            badgeTitle = itemView.findViewById(R.id.badge_title);
            badgeImage = itemView.findViewById(R.id.badge_image);
            badgeCard = itemView.findViewById(R.id.badge_card);
        }
    }
}
