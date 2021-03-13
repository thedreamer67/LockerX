package com.example.lockerxlogin.fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lockerxlogin.BookingActivity;
import com.example.lockerxlogin.BookingHistoryArr;
import com.example.lockerxlogin.MainActivity;
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
                //intent =  new Intent(mContext, Register.class);
                //TODO put if else statement to compare time here
                if(bha.getStatus().equals("R")){//Returned locker
                    holder.getStatus().setTextColor(Color.parseColor("#fc0f03"));
                } else if (bha.getStatus().equals("B")){// future
                    holder.getStatus().setTextColor(Color.parseColor("#1df511"));
                    showDialogForFuture(v);

                } else if (bha.getStatus().equals("O")) {
                    holder.getStatus().setTextColor(Color.parseColor("#1df530"));
                    showDialogPresent(v);
                }else if (bha.getStatus().equals("C")) {
                    holder.getStatus().setTextColor(Color.parseColor("#1df520"));
                }
               //


                Log.d("TAG","The booking id before passed is " + bha.getBookingId());
                //intent.putExtra("variableToPass", bha.getBookingId());
              //  mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return bookingHistoryArr.size();
    }

    public void showDialogForFuture(View view){
        final androidx.appcompat.app.AlertDialog.Builder continueBookingDialog = new AlertDialog.Builder(view.getContext());
        continueBookingDialog.setTitle("What option would you like to do?");
        // resendVerificationMailDialog.setView(resendVerificationEditText);
        continueBookingDialog.setPositiveButton("Cancel Booking", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Automatically close the dialog
                Context mContext = view.getContext();
                final Intent intent;
                //TODO - go to cancelled locker booking activity class.
                intent =  new Intent(mContext, MainActivity.class);
                /* Mayb transfer variable
                intent.putExtra("title", marker.getTitle());
                intent.putExtra("postal",post);*/

                mContext.startActivity(intent);



            }
        });
        continueBookingDialog.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        continueBookingDialog.show();
    }

    public void showDialogPresent(View view){
        final androidx.appcompat.app.AlertDialog.Builder continueBookingDialog = new AlertDialog.Builder(view.getContext());
        continueBookingDialog.setTitle("What would you like to do?");
        // resendVerificationMailDialog.setView(resendVerificationEditText);
        continueBookingDialog.setPositiveButton("Return Locker", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Automatically close the dialog
                Context mContext = view.getContext();
                final Intent intent;
                //TODO return locker activity
                intent =  new Intent(mContext, MainActivity.class);
                /* Mayb transfer variable
                intent.putExtra("title", marker.getTitle());
                intent.putExtra("postal",post);*/

                mContext.startActivity(intent);



            }
        });
        continueBookingDialog.setNegativeButton("Lock/Unlock", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Context mContext = view.getContext();
                final Intent intent;

                intent =  new Intent(mContext, MainActivity.class);
                /* Mayb transfer variable
                intent.putExtra("title", marker.getTitle());
                intent.putExtra("postal",post);*/

                mContext.startActivity(intent);


            }
        });

        continueBookingDialog.show();
    }

}
