package com.example.lockerxlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.lockerxlogin.fragment.AccountsFragment;
import com.example.lockerxlogin.fragment.HomeFragment;
import com.example.lockerxlogin.fragment.LockersFragment;
import com.example.lockerxlogin.fragment.WalletFragment;
import androidx.fragment.app.FragmentPagerAdapter;
import com.google.android.gms.maps.MapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.view.MenuItem;
import androidx.viewpager.widget.ViewPager;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import android.view.*;
import android.widget.Toast;

public class MainFunc extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth fAuth;
    FirebaseUser FBuser;
    ViewPager viewPager;
    BottomNavigationView mNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    LockersFragment lockersFragment = new LockersFragment();
    WalletFragment walletFragment = new WalletFragment();
    AccountsFragment accountsFragment = new AccountsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        init();
    }

    private void init() {
        viewPager = findViewById(R.id.viewPager);
       // viewPager.addOnPageChangeListener(this);
        mNavigationView = findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(this);

        //switch

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch(position) {
                    case 0:
                        return homeFragment;
                    case 1:
                        return lockersFragment;
                    case 2:
                        return walletFragment;
                    case 3:
                        return accountsFragment;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }

//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//            mNavigationView.getMenu().getItem(position).setChecked(true);
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
            viewPager.setCurrentItem(menuItem.getOrder());
            return true;
        }
    }
//        viewPager = findViewById(R.id.viewPager);
//        viewPager.addOnPageChangeListener(this);
//        BottomNavigationView bottomNav = findViewById(R.id.navigation);
//        bottomNav.setOnNavigationItemSelectedListener(navListener);
//        //I added this if statement to keep the selected fragment when rotating the device
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new HomeFragment()).commit();
//        }
//    }
//    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    Fragment selectedFragment = null;
//                    switch (item.getItemId()) {
//                        case R.id.navigation_home:
//                            selectedFragment = new HomeFragment();
//                            break;
//                        case R.id.navigation_lockers:
//                            selectedFragment = new LockersFragment();
//                            break;
//                        case R.id.navigation_wallet:
//                            selectedFragment = new WalletFragment();
//                            break;
//
//                        case R.id.navigation_account:
//                            selectedFragment = new AccountsFragment();
//                            break;
//                    }
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                            selectedFragment).commit();
//                    return true;
//                }
//            };
//
//}
//
//
//
