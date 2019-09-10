package com.asaptemp.provider.tour.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.asaptemp.provider.R;
import com.asaptemp.provider.tour.TourModel;

import java.util.ArrayList;

public class CustomPagerAdapter extends PagerAdapter {
    private ArrayList<TourModel> tourModelArrayList;
    private LayoutInflater inflater;
    private Context context;

    public CustomPagerAdapter(Context context,ArrayList<TourModel> tourModelArrayList) {
        this.context = context;
        this.tourModelArrayList=tourModelArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return tourModelArrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.row_view_pager_item, view, false);
        ImageView myImage = myImageLayout.findViewById(R.id.imgTour);
        TextView txtTitle = myImageLayout.findViewById(R.id.txtTitle);
        TextView txtMsg = myImageLayout.findViewById(R.id.txtMsg);

        txtTitle.setText(tourModelArrayList.get(position).getTitle());
        txtMsg.setText(tourModelArrayList.get(position).getMsg());

        myImage.setImageResource(tourModelArrayList.get(position).getPic());
        view.addView(myImageLayout, 0);

        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
