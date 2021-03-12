package com.example.lockerxlogin.fragment;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lockerxlogin.BookingHistoryArr;
import com.example.lockerxlogin.R;
import com.example.lockerxlogin.ui.BookingSearchArr;

import java.util.ArrayList;

public class BookingSearchArrAdapter extends RecyclerView.Adapter<BookingSearchRecyclerViewHolder> {
    private ArrayList<BookingSearchArr> bookingSearchArr;

    public BookingSearchArrAdapter(ArrayList<BookingSearchArr> bookingSearchArr1) {

        this.bookingSearchArr = bookingSearchArr1;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.booking_search_item;
    }

    @NonNull
    @Override
    public BookingSearchRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingSearchRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
