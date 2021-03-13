package com.example.lockerxlogin.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lockerxlogin.BookingHistoryArr;
import com.example.lockerxlogin.DatabaseController;
import com.example.lockerxlogin.Login;
import com.example.lockerxlogin.MainActivity;
import com.example.lockerxlogin.MainFunc;
import com.example.lockerxlogin.R;
import com.example.lockerxlogin.Viewholder_Booking;
import com.example.lockerxlogin.ui.lockers.LockersViewModel;

import java.util.ArrayList;

public class LockersFragment extends Fragment implements View.OnClickListener {

    Button LockerModeBtn;


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Handler mainHandler = new Handler();
    private volatile boolean stopThread = false;



    private LockersViewModel mViewModel;

    public static LockersFragment newInstance() {
        return new LockersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_lockers, container, false);
        //Start multi thread here.
        LockersFragment.RetrieveBookingThread runnable = new LockersFragment.RetrieveBookingThread();
        new Thread(runnable).start();
        ArrayList<Viewholder_Booking> exampleList = new ArrayList<>();
        exampleList.add(new Viewholder_Booking( "Line 1", "Line 2"));
        exampleList.add(new Viewholder_Booking( "Line 3", "Line 4"));
        exampleList.add(new Viewholder_Booking( "Line 5", "Line 6"));

        ArrayList<BookingHistoryArr> bookingHistoryArr = new ArrayList<BookingHistoryArr>();
        bookingHistoryArr.add(new BookingHistoryArr("1","2021-02-17",
                "16:00:00","3","91237777", "2021-02-17",
                "13:00:00","O", "1"));
        bookingHistoryArr.add(new BookingHistoryArr("2","2021-03-02",
                "16:00:00","2","91237777", "2021-03-02",
                "14:00:00","R", "2"));
        bookingHistoryArr.add(new BookingHistoryArr("3","2021-02-19",
                "13:00:00","1","90059608", "2021-02-19",
                "12:00:00","R", "1"));
        bookingHistoryArr.add(new BookingHistoryArr("4","2021-04-21",
                "19:00:00","3","90059608", "2021-04-21",
                "13:00:00","B", "2"));

        ArrayList <String> lockerArray = new ArrayList<String>();
        mRecyclerView = myView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));
        mRecyclerView.setAdapter(new BookingHistoryArrAdapter(bookingHistoryArr));
      //  mRecyclerView.setHasFixedSize(true);

       // mAdapter = new ExampleAdapter(exampleList);
       // mRecyclerView.setLayoutManager(mLayoutManager);
       // mRecyclerView.setAdapter(mAdapter);

        //mRecyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));
        //mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.rvBooking);
//        LockerModeBtn = myView.findViewById(R.id.LLockerMode);
//        LockerModeBtn.setOnClickListener(this);
//        lockerArray.add("testLockerID");

//        mLayoutManager = new LinearLayoutManager(getActivity());
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

    class RetrieveBookingThread implements Runnable{
        RetrieveBookingThread(){
            //
        }

        @Override
        public void run() {
            Log.d("TAG", "Adding progress bar");
            //textViewForProgressBar.setVisibility(View.VISIBLE);



            for (int i = 0; i < 10000; i++) {

//

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }






                }
            stopThread = true;
            if (stopThread){
                //dbProgressBar.setVisibility(View.GONE);
                Log.d("TAG", "Stopping thread");

                return;}

            }

        }


    public void startThread(View view){
        stopThread = false;
        LockersFragment.RetrieveBookingThread runnable = new LockersFragment.RetrieveBookingThread();
        new Thread(runnable).start();

    }
    public void stopThread(View view){
        stopThread = true;
    }
}




