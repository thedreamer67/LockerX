package com.example.lockerxlogin.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lockerxlogin.MainActivity;
import com.example.lockerxlogin.R;
import com.example.lockerxlogin.Register;
import com.example.lockerxlogin.ui.lockers.LockersViewModel;

public class LockersFragment extends Fragment implements View.OnClickListener {

    Button LockerModeBtn;

    private LockersViewModel mViewModel;

    public static LockersFragment newInstance() {
        return new LockersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       // LockerModeBtn = getView().findViewById(R.id.LLockerMode);
        //LockerModeBtn.setOnClickListener(this);



        return inflater.inflate(R.layout.fragment_lockers, container, false);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

  /*  @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LockersViewModel.class);
        // TODO: Use the ViewModel
    }*/


}