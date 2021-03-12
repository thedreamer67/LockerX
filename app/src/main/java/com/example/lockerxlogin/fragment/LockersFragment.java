package com.example.lockerxlogin.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lockerxlogin.BookingModel;
import com.example.lockerxlogin.MainActivity;
import com.example.lockerxlogin.R;
import com.example.lockerxlogin.Viewholder_Booking;
import com.example.lockerxlogin.ui.lockers.LockersViewModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LockersFragment extends Fragment implements View.OnClickListener {

    Button LockerModeBtn;
    ArrayList<String> lockerArray;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;


    private LockersViewModel mViewModel;

    public static LockersFragment newInstance() {
        return new LockersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ArrayList <String> lockerArray = new ArrayList<String>();
        View myView = inflater.inflate(R.layout.fragment_lockers, container, false);
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.rvBooking);
        LockerModeBtn = myView.findViewById(R.id.LLockerMode);
        LockerModeBtn.setOnClickListener(this);
        lockerArray.add("testLockerID");

        mLayoutManager = new LinearLayoutManager(getActivity());
      //  mAdapter = new MainAdapter(lockerArray);
      //  mRecyclerView.setLayoutManager(mLayoutManager);
     //   mRecyclerView.setAdapter(mAdapter);

        return myView;
    }
    //
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LLockerMode:
                this.startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


}