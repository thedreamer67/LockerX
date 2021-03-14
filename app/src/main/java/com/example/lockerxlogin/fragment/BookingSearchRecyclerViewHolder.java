package com.example.lockerxlogin.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lockerxlogin.R;

public class BookingSearchRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView bookingID, lockerID, startTime, startDate, endDate, endTime, status, structure, mobile;

    public BookingSearchRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        bookingID = itemView.findViewById(R.id.search_booking_id_item_tv);
        startTime = itemView.findViewById(R.id.search_start_time_item_tv);
        startDate = itemView.findViewById(R.id.search_start_date_item_tv);
        endDate = itemView.findViewById(R.id.search_end_date_item_tv);
        endTime = itemView.findViewById(R.id.search_end_time_item_tv);
        lockerID = itemView.findViewById(R.id.search_locker_id_item_tv);
        status = itemView.findViewById(R.id.search_status_item_tv);
        structure = itemView.findViewById(R.id.search_structure_id_item_tv);
        mobile = itemView.findViewById(R.id.search_mobile_item_tv);
    }


    public TextView getStartTime() {
        return startTime;
    }

    public TextView getStartDate() {
        return startDate;
    }

    public TextView getEndDate() {
        return endDate;
    }

    public TextView getEndTime() {
        return endTime;
    }

    public TextView getStatus() {
        return status;
    }

    public TextView getStructure() {
        return structure;
    }

    public TextView getMobile() {
        return mobile;
    }

    public TextView getBookingID() { return bookingID; }

    public TextView getLockerID() { return lockerID; }
}
