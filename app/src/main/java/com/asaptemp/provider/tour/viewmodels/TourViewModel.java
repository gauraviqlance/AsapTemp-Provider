package com.asaptemp.provider.tour.viewmodels;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.asaptemp.provider.R;
import com.asaptemp.provider.login.LoginActivity;
import com.asaptemp.provider.tour.TourModel;


public class TourViewModel extends BaseObservable {
    private Activity activity;
    private TourModel tourModel;
    private int currentPage =0;

    /* ------------------------------ Constructor */

    public TourViewModel(@NonNull Activity activity) {
        this.activity = activity;
        tourModel = tourModel = new TourModel(R.drawable.tour_screen_icon_1,activity.getResources().getString(R.string.tour_title_1),activity.getResources().getString(R.string.tour_msg_1));

    }

    public TourViewModel(){
        tourModel = new TourModel();
    }

    private MutableLiveData<TourModel> tourModelMutableLiveData;

    LiveData<TourModel> getTour() {
        if (tourModelMutableLiveData == null) {
            tourModelMutableLiveData = new MutableLiveData<>();
        }

        return tourModelMutableLiveData;
    }

    public void onBtnContinueClicked(){
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);

    }




}
