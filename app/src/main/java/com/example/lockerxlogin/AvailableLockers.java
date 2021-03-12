package com.example.lockerxlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.lockerxlogin.fragment.BookingHistoryArrAdapter;

import java.util.ArrayList;

public class AvailableLockers extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_lockers);
        ArrayList<Viewholder_Booking> exampleList = new ArrayList<>();
        exampleList.add(new Viewholder_Booking( "Line 1", "Line 2"));
        exampleList.add(new Viewholder_Booking( "Line 3", "Line 4"));
        exampleList.add(new Viewholder_Booking( "Line 5", "Line 6"));

        ArrayList<BookingHistoryArr> bookingHistoryArr = new ArrayList<BookingHistoryArr>();
        bookingHistoryArr.add(new BookingHistoryArr("1","2021-02-17",
                "16:00:00","3","91237777", "2021-02-17",
                "13:00:00","R", "1"));
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
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    }
}