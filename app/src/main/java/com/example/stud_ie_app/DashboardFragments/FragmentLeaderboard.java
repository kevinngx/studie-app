package com.example.stud_ie_app.DashboardFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stud_ie_app.RecyclerViewAdapters.LeaderboardRecyclerViewAdapter;
import com.example.stud_ie_app.R;
import com.example.stud_ie_app.DatabaseClasses.SessionData;
import com.example.stud_ie_app.Users;

import java.util.List;

public class FragmentLeaderboard extends Fragment {

    View view;
    private RecyclerView leaderboardRecyclerView;
    private List<Users> mUsers;

    public FragmentLeaderboard() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.leaderboard_fragment, container, false);
        leaderboardRecyclerView = (RecyclerView) view.findViewById(R.id.leaderboard_recyclerview);
        LeaderboardRecyclerViewAdapter recyclerViewAdapter = new LeaderboardRecyclerViewAdapter(getContext(), mUsers);
        leaderboardRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        leaderboardRecyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUsers = SessionData.mUserDatabase.mUserDao().getAll();
    }
}
