package com.example.lockerxlogin;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Viewholder_Booking{//
        // extends RecyclerView.ViewHolder {
    private int mImagineResource;

    private int mImageResource;
    private String mText1;
    private String mText2;
    public Viewholder_Booking( String text1, String text2) {

        mText1 = text1;
        mText2 = text2;
    }
    public int getImageResource() {
        return mImageResource;
    }
    public String getText1() {
        return mText1;
    }
    public String getText2() {
        return mText2;
    }


 /*   TextView bookingIdResult, startTimeResult, startDateResult, endDateResult, endTimeResult, lockerIdResult, statusResult, structureResult, view_booking;
    public Viewholder_Booking(@NonNull View itemView) {
        super(itemView);
    }

    public void setitem(FragmentActivity activity, String bookingId, String startTime, String startDate, String endDate, String endTime, String lockerId, String status, String structure){
        bookingIdResult = itemView.findViewById(R.id.booking_id_item_tv);
        startTimeResult = itemView.findViewById(R.id.start_time_item_tv);
        startDateResult = itemView.findViewById(R.id.start_date_item_tv);
        endDateResult = itemView.findViewById(R.id.end_date_item_tv);
        endTimeResult = itemView.findViewById(R.id.end_time_item_tv);
        lockerIdResult = itemView.findViewById(R.id.locker_id_item_tv);
        statusResult = itemView.findViewById(R.id.status_item_tv);
        structureResult = itemView.findViewById(R.id.structure_id_item_tv);
        view_booking = itemView.findViewById(R.id.view_booking);

        bookingIdResult.setText(bookingId);
        startTimeResult.setText(startTime);
        startDateResult.setText(startDate);
        endDateResult.setText(endDate);
        endTimeResult.setText(endTime);
        lockerIdResult.setText(lockerId);
        statusResult.setText(status);
        structureResult.setText(structure);

    }*/
}
