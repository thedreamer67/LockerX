package com.example.lockerxlogin.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lockerxlogin.MainActivity;
import com.example.lockerxlogin.R;
import com.example.lockerxlogin.TopUpPage;
import com.example.lockerxlogin.ui.wallet.WalletViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WalletFragment extends Fragment implements View.OnClickListener{
    TextView walletBalance;
    Button topUpButton;
    private WalletViewModel mViewModel;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    DatabaseReference reff;

    public static WalletFragment newInstance() {
        return new WalletFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_wallet, container, false);
        walletBalance = myView.findViewById(R.id.walletBalance);
        topUpButton = myView.findViewById(R.id.topUpButton);
        topUpButton.setOnClickListener(this);

        reff = FirebaseDatabase.getInstance().getReference().child("User").child("12345678");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String balance = snapshot.child("walletBalance").getValue().toString();
                String FirstLine = ("You have \n$ ");
                walletBalance.setText(FirstLine + balance);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return myView;
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.topUpButton:
                this.startActivity(new Intent(getActivity(), TopUpPage.class));
                break;
        }

    }


   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WalletViewModel.class);
        // TODO: Use the ViewModel
    }*/

}