package com.example.lockerxlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class AvailableLockers extends AppCompatActivity {

    ArrayList<Locker> lockerlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_lockers);

        lockerlist = (ArrayList<Locker>) getIntent().getSerializableExtra("mylist");
        Log.d("tab",lockerlist.get(0).getAddress());

    }
}