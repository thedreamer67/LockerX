package com.example.lockerxlogin.fragment;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lockerxlogin.BookingHistoryArr;
import com.example.lockerxlogin.R;
import com.example.lockerxlogin.Register;

import java.util.ArrayList;

public class BookingHistoryArrAdapter extends RecyclerView.Adapter<BookingHistoryRecyclerViewHolder> {
    private ArrayList<BookingHistoryArr> bookingHistoryArr;

    public BookingHistoryArrAdapter(ArrayList<BookingHistoryArr> bookingHistoryArr1) {

        this.bookingHistoryArr = bookingHistoryArr1;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.booking_history_item;
    }

    @NonNull
    @Override
    public BookingHistoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new BookingHistoryRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHistoryRecyclerViewHolder holder, int position) {
        BookingHistoryArr bha = bookingHistoryArr.get(position);
        holder.getBookingID().setText("Booking id: " + bha.getBookingId());
        holder.getLockerID().setText("Locker id: " + bha.getLockerID());
        holder.getEndDate().setText("End date: " + bha.getEndDate());
        holder.getEndTime().setText("End time: " + bha.getEndTime());
        holder.getMobile().setText("Mobile: " + bha.getMobile());
        holder.getStartDate().setText("Start date: " + bha.getStartDate());
        holder.getStartTime().setText("Start time: " + bha.getStartTime());
        holder.getStatus().setText("Status: " + bha.getStatus());
        holder.getStructure().setText("Structure ID: " + bha.getStructureID());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mContext = v.getContext();
                final Intent intent;
                intent =  new Intent(mContext, Register.class);

                Log.d("TAG","The booking id before passed is " + bha.getBookingId());
                intent.putExtra("variableToPass", bha.getBookingId());
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return bookingHistoryArr.size();
    }
}
