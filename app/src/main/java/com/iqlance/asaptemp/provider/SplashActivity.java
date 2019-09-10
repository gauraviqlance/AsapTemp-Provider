package com.iqlance.asaptemp.provider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.iqlance.asaptemp.provider.login.LoginActivity;
import com.iqlance.asaptemp.provider.tour.TourActivity;
import com.iqlance.asaptemp.provider.utils.Preference;


public class SplashActivity extends AppCompatActivity {

    private Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preference = new Preference(SplashActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i;
                if (preference.isLoggedIn()) {
                    i = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    if(preference.getIsFirstTimeTour()) {
                        i = new Intent(SplashActivity.this, LoginActivity.class);
                    }else{
                        i = new Intent(SplashActivity.this, TourActivity.class);
                    }
                }
               // preference.setIsFirstTimeTour(true);
                startActivity(i);
                finish();
            }


        }, 2000);
    }


}
