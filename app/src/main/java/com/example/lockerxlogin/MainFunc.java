package com.example.lockerxlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.lockerxlogin.ui.home.HomeFragment;
import com.example.lockerxlogin.ui.accounts.AccountsFragment;
import com.example.lockerxlogin.ui.lockers.LockersFragment;
import com.example.lockerxlogin.ui.wallet.WalletFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import android.view.*;

public class MainFunc extends AppCompatActivity {

    ViewPager viewPager;
    BottomNavigationView mNavigationView;
    HomeFragment  homeFragments = new HomeFragment();
    LockersFragment lockersFragments = new LockersFragment();
    WalletFragment walletFragments = new WalletFragment();
    AccountsFragment accountFragments = new AccountsFragment();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:

                return true;
            case R.id.navigation_lockers:

                return true;
            case R.id.navigation_wallet:

                return true;
            case R.id.navigation_account:
                return true;

        }
        return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }


}



