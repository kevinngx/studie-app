package com.example.stud_ie_app.RecyclerViewAdapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stud_ie_app.Badges;
import com.example.stud_ie_app.DatabaseClasses.ImageBank;
import com.example.stud_ie_app.R;
import com.example.stud_ie_app.DatabaseClasses.SessionData;
import com.example.stud_ie_app.Users;
import com.example.stud_ie_app.UsrBadges;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardRecyclerViewAdapter extends RecyclerView.Adapter<LeaderboardRecyclerViewAdapter.LeaderboardViewHolder> {

    Context mContext;
    List<Users> mData;
    Dialog mDialog;

    // Nested RecyclerView Stuff
    ImageView leaderboardAvatar;
    TextView leaderboardUsername;
    TextView leaderboardRole;
    TextView leaderboardPoints;
    private List<Badges> mBadges;

    public LeaderboardRecyclerViewAdapter(Context context, List<Users> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView;
        myView = LayoutInflater.from(mContext).inflate(R.layout.leaderboard_user_card, parent, false);
        LeaderboardViewHolder viewHolder = new LeaderboardViewHolder(myView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final LeaderboardViewHolder viewHolder, final int position) {
        if (mData.get(position).getUserName().equals(SessionData.currentUser.getUserName())) {
            viewHolder.userCard.setCardBackgroundColor(Color.parseColor("#ABB4BE"));
        } else {
            viewHolder.userCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        viewHolder.username.setText(mData.get(position).getUserName());
        viewHolder.role.setText(mData.get(position).getRole());
        viewHolder.score.setText(Integer.toString(mData.get(position).getScore()));
        viewHolder.rank.setText(Integer.toString(position + 1));
        int[] avatars = {
                R.drawable.avatar0,
                R.drawable.avatar1,
                R.drawable.avatar2,
                R.drawable.avatar3,
                R.drawable.avatar4,
                R.drawable.avatar5,
                R.drawable.avatar6,
                R.drawable.avatar7,
                R.drawable.avatar8,
        };
        viewHolder.avatar.setImageResource(avatars[mData.get(position).getAvatar()]);

        // Opens up a detail view of the selected user, displaying their collection
        viewHolder.userCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = new Dialog(mContext);
                mDialog.setContentView(R.layout.popup_leaderboard);
                Button popupBack = (Button) mDialog.findViewById(R.id.popup_back);

                RecyclerView badgeRecyclerView;

                leaderboardAvatar = (ImageView) mDialog.findViewById(R.id.leaderboard_avatar);
                leaderboardUsername = (TextView) mDialog.findViewById(R.id.leaderboard_username);
                leaderboardRole = (TextView) mDialog.findViewById(R.id.leaderboard_role);
                leaderboardPoints = (TextView) mDialog.findViewById(R.id.leaderboard_score);

                String selectedUser = mData.get(position).getUserName();
                leaderboardPoints.setText(Integer.toString(mData.get(position).getScore()));
                leaderboardUsername.setText(mData.get(position).getUserName());
                leaderboardAvatar.setImageResource(ImageBank.avatars[mData.get(position).getAvatar()]);
                leaderboardRole.setText(mData.get(position).getRole());

                // Setup RecyclerView of their badges to choose from
                List<UsrBadges> userBadges = SessionData.mUsrBadgesDatabase.mUsrBadgesDao().getAllBadgesByUser(selectedUser);
                mBadges = new ArrayList<Badges>();
                for (int i = 0; i < userBadges.size(); i++) {
                    mBadges.add(SessionData.mBadgeDatabase.mBadgeDao().fetchBadgeByID(userBadges.get(i).getBadgeID()));
                }

                badgeRecyclerView = (RecyclerView) mDialog.findViewById(R.id.leaderboard_badge_recyclerview);
                badgeRecyclerView.setHasFixedSize(true);
                BadgeRecyclerViewAdapter recyclerViewAdapter = new BadgeRecyclerViewAdapter(mDialog.getContext(), mBadges);
                badgeRecyclerView.setLayoutManager(new LinearLayoutManager(mDialog.getContext(), LinearLayoutManager.HORIZONTAL, false));
                badgeRecyclerView.setAdapter(recyclerViewAdapter);

                // Dismiss Button
                popupBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialog.dismiss();
                    }
                });

                // Launch dialog window
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                mDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class LeaderboardViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private TextView role;
        private TextView score;
        private TextView rank;
        private ImageView avatar;
        private CardView userCard;

        public LeaderboardViewHolder(View itemView) {
            super(itemView);

            username = (TextView) itemView.findViewById(R.id.leaderboard_username);
            role = (TextView) itemView.findViewById(R.id.user_role);
            score = (TextView) itemView.findViewById(R.id.leaderboard_user_score);
            rank = (TextView) itemView.findViewById(R.id.leaderboard_ranking);
            avatar = (ImageView) itemView.findViewById(R.id.user_avatar);
            userCard = (CardView) itemView.findViewById(R.id.leaderboard_employee_id);

        }
    }
}
