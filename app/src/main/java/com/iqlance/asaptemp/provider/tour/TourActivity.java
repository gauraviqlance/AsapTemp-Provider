package com.iqlance.asaptemp.provider.tour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.iqlance.asaptemp.provider.R;
import com.iqlance.asaptemp.provider.login.LoginActivity;
import com.iqlance.asaptemp.provider.tour.adapter.CustomPagerAdapter;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

public class TourActivity extends AppCompatActivity {

    private ArrayList<TourModel> tourModelArrayList;
   // private ActivityTourBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // binding = DataBindingUtil.setContentView(this, R.layout.activity_tour);
        setContentView(R.layout.activity_tour);
        initView();
    }

    private void initView() {
        tourModelArrayList = new ArrayList<>();
        tourModelArrayList.add(new TourModel(R.drawable.tour_screen_icon_1,
                getResources().getString(R.string.tour_title_1),
                getResources().getString(R.string.tour_msg_1) ));
        tourModelArrayList.add(new TourModel(R.drawable.tour_screen_icon_2,
                getResources().getString(R.string.tour_title_2),
                getResources().getString(R.string.tour_msg_2)

        ));
        tourModelArrayList.add(new TourModel(R.drawable.tour_screen_icon_3, getResources().getString(R.string.tour_title_3), getResources().getString(R.string.tour_msg_3)));


       // TourViewModel tourViewModel = new TourViewModel(TourActivity.this);
       // binding.setViewModel(tourViewModel);
       // binding.setLifecycleOwner(this);


        ViewPager viewPager = findViewById(R.id.viewPagerTour);
        viewPager.setAdapter(new CustomPagerAdapter(this, tourModelArrayList));
        final PageIndicatorView pageIndicatorView = findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setCount(tourModelArrayList.size());
        //pageIndicatorView.setDynamicCount(true);
        pageIndicatorView.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                pageIndicatorView.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ConstraintLayout constrainContinue = findViewById(R.id.constrainContinue);
        if(constrainContinue!=null){
            constrainContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TourActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
}
