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

  /*  @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LockersViewModel.class);
        // TODO: Use the ViewModel
    }*/


}