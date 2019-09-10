package com.iqlance.asaptemp.provider;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.iqlance.asaptemp.provider.job.JobFragment;
import com.iqlance.asaptemp.provider.jobhistory.JobHistoryFragment;
import com.iqlance.asaptemp.provider.moresettings.MoreSettingFragment;
import com.iqlance.asaptemp.provider.profile.ProfileFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements BottomNavigationBar.OnTabSelectedListener{

    private int lastSelectedPosition = 0;
    private BottomNavigationBar bottomNavigationBar;

    private boolean isContinue = false;
    private boolean isGPS = false;

    //google map
    private FusedLocationProviderClient mFusedLocationClient;
    public static double wayLatitude = 0.0, wayLongitude = 0.0;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private StringBuilder stringBuilder;

    JobFragment jobFragment;
    JobHistoryFragment jobHistoryFragment;
    ProfileFragment profileFragment;
    MoreSettingFragment moreSettingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(this);


        jobFragment = new JobFragment();
        jobHistoryFragment = new JobHistoryFragment();
        profileFragment= new ProfileFragment();
        moreSettingFragment = new MoreSettingFragment();

        setScrollableText(lastSelectedPosition);

        bottomNavigationBar.setBarBackgroundColor(R.color.colorWhite);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.selector_home, getResources().getString(R.string.job)).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.colorAccent)).initialise();
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.selector_my_booking, getResources().getString(R.string.history)).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.colorAccent)).initialise();
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.selector_notification, getResources().getString(R.string.profile)).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.colorAccent)).initialise();
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.selector_profile, getResources().getString(R.string.more)).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.colorAccent)).initialise();
        bottomNavigationBar.setAutoHideEnabled(false);

    }

    @Override
    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        setScrollableText(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private void setScrollableText(int position) {
        switch (position) {
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, jobHistoryFragment).commitAllowingStateLoss();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, profileFragment).commitAllowingStateLoss();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, moreSettingFragment).commitAllowingStateLoss();
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, jobFragment).commitAllowingStateLoss();
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
