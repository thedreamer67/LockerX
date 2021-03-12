package com.example.lockerxlogin.fragment;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lockerxlogin.R;

import org.w3c.dom.Text;

public class BookingHistoryRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView bookingID, lockerID, startTime, startDate, endDate, endTime, status, structure, mobile;
    public BookingHistoryRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        bookingID = itemView.findViewById(R.id.booking_id_item_tv);
        startTime = itemView.findViewById(R.id.start_time_item_tv);
        startDate = itemView.findViewById(R.id.start_date_item_tv);
        endDate = itemView.findViewById(R.id.end_date_item_tv);
        endTime = itemView.findViewById(R.id.end_time_item_tv);
        lockerID = itemView.findViewById(R.id.locker_id_item_tv);
        status = itemView.findViewById(R.id.status_item_tv);
        structure = itemView.findViewById(R.id.structure_id_item_tv);
        mobile = itemView.findViewById(R.id.mobile_item_tv);
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

    public TextView getBookingID(){
        return bookingID;
    }

    public TextView getLockerID(){
        return lockerID;
    }
}
