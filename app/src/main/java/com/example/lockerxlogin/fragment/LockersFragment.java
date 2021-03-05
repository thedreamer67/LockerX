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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.lockerxlogin.BookingModel;
import com.example.lockerxlogin.MainActivity;
import com.example.lockerxlogin.R;
import com.example.lockerxlogin.Viewholder_Booking;
import com.example.lockerxlogin.ui.lockers.LockersViewModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LockersFragment extends Fragment implements View.OnClickListener {

    Button LockerModeBtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    RecyclerView recyclerView;


    private LockersViewModel mViewModel;

    public static LockersFragment newInstance() {
        return new LockersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_lockers, container, false);
        LockerModeBtn = myView.findViewById(R.id.LLockerMode);
        LockerModeBtn.setOnClickListener(this);

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

        recyclerView = getActivity().findViewById(R.id.rvBooking);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        databaseReference = database.getReference().child("Booking");

        FirebaseRecyclerOptions<BookingModel> options = new FirebaseRecyclerOptions.Builder<BookingModel>().setQuery(databaseReference, BookingModel.class).build();
        FirebaseRecyclerAdapter<BookingModel, Viewholder_Booking> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<BookingModel, Viewholder_Booking>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Viewholder_Booking holder, int position, @NonNull BookingModel model) {
                        holder.setitem(getActivity(), model.getBookingId(), model.getStartTime(), model.getStartDate(), model.getEndDate(),model.getEndTime(), model.getLockerId(),model.getStatus(),model.getStructure());

                        final String bookingId = getItem(position).getBookingId();
                        final String startTime = getItem(position).getStartTime();
                        final String startDate = getItem(position).getStartDate();
                        final String endDate = getItem(position).getEndDate();
                        final String endTime = getItem(position).getEndTime();
                        final String lockerId = getItem(position).getLockerId();
                        final String status = getItem(position).getStatus();
                        final String structure = getItem(position).getStructure();


                    }

                    @NonNull
                    @Override
                    public Viewholder_Booking onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item,parent,false);
                        return new Viewholder_Booking(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }


}