package com.example.lockerxlogin.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lockerxlogin.MainFunc;
import com.example.lockerxlogin.R;
import com.example.lockerxlogin.ui.accounts.AccountsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountsFragment extends Fragment  {

    private AccountsViewModel mViewModel;
    String userEmail;
    String mobileNum;

    public static AccountsFragment newInstance() {
        return new AccountsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_accounts, container, false);
        TextView textViewEditProfileID = (TextView) myView.findViewById(R.id.textViewNameID);
        TextView textViewEmailID = (TextView) myView.findViewById(R.id.textViewEmailID);
        TextView textViewSettingsID = (TextView) myView.findViewById(R.id.textViewSettingsID);
        TextView textViewMobileNumID = (TextView) myView.findViewById(R.id.textViewMobileNumID);
        TextView textViewNameID = (TextView) myView.findViewById(R.id.textViewNameID);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail = user.getEmail();
        textViewEditProfileID.setText(userEmail);

//        String title = getArguments().getString("title");
     //   Toast.makeText(getActivity(),title +" BRUH.",Toast.LENGTH_SHORT)
             //   .show();

        textViewMobileNumID.setText(user.getPhoneNumber());
        //String markerLocation = getActivity().getIntent().getStringExtra("selectedLocationString");
        //Toast.makeText(getActivity(),markerLocation,Toast.LENGTH_SHORT).show();



        return myView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AccountsViewModel.class);
        // TODO: Use the ViewModel
    }


}