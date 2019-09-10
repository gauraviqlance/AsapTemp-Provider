package com.iqlance.asaptemp.provider.job;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.iqlance.asaptemp.provider.R;

import java.util.ArrayList;

import butterknife.BindView;

public class NewJobsFragment extends Fragment {
    private static final int SWIPE_REFRESH_DELAY_TIME = 6000;

    boolean loading = false;
    boolean isFromSwipe = false;

    private Context context;
    private View view;


    private JobsAdapter adapter;
    private ArrayList<JobsModel> jobsModelArrayList;


    @BindView(R.id.progressBarNewJobs)
    ProgressBar progressBarNewJobs;
    @BindView(R.id.swipeRefreshNewJobs)
    SwipeRefreshLayout swipeRefreshNewJobs;
    @BindView(R.id.recyclerViewNewJobs)
    RecyclerView recyclerViewNewJobs;

    private LinearLayoutManager mLayoutManager;

    int pastVisiblesItems, visibleItemCount, totalItemCount;

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (dy > 0) //check for scroll down
            {
                updateLoader(true);
                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = false;
                        // if current page++; here.
                        Log.v("...", "Last Item Wow !");
                        //Do pagination.. i.e. fetch new data
                        // newData();
                    }
                }

                updateLoader(false);
            }
        }
    };

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    // Stop animation (This will be after 3 seconds)
                    isFromSwipe = true;
                    swipeRefreshNewJobs.setRefreshing(true);
                    adapter.clearList();
                    setUpBookingList();
                    swipeRefreshNewJobs.setRefreshing(false);
                    isFromSwipe = false;

                }

            }, SWIPE_REFRESH_DELAY_TIME); // Delay in millis

        }
    };



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_new_jobs, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {

        if (swipeRefreshNewJobs != null) {
            swipeRefreshNewJobs.setOnRefreshListener(onRefreshListener);
            // Scheme colors for animation

            swipeRefreshNewJobs.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent, R.color.colorPrimaryDark);
        }


        jobsModelArrayList = new ArrayList<>();
        adapter = new JobsAdapter(context, jobsModelArrayList, new JobsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                gotoBookingDetailActivity();

            }
        });

        if(recyclerViewNewJobs!=null) {
            mLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            recyclerViewNewJobs.setLayoutManager(mLayoutManager);
            recyclerViewNewJobs.setItemAnimator(new DefaultItemAnimator());
            recyclerViewNewJobs.setAdapter(adapter);
            recyclerViewNewJobs.addOnScrollListener(onScrollListener);

        }
        setUpBookingList();
    }

    private void gotoBookingDetailActivity() {
       // Intent intent = new Intent(context, BookingDetailActivity.class);
       // startActivity(intent);
    }

    private void setUpBookingList() {

        if(isFromSwipe){
            jobsModelArrayList.add( new JobsModel(0,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(0,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Kamal","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
        }else{
            jobsModelArrayList.add( new JobsModel(0,"Raju","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Pankaj","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Pranav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
            jobsModelArrayList.add( new JobsModel(1,"Gaurav","30min Ago","Air Conditioner","Air condition repaera at home","April 12 2019","5:30 - 6:30 Pm","$ 25.00- $ 30.00","4.2 Kms","A-406 Safal Pegisus PrhladNagar Ahmedabad"));
        }

        //mLayoutManager.scrollToPositionWithOffset(0, 0);
      //  recyclerViewNewJobs.smoothScrollToPosition(jobsModelArrayList.size()-1);
        adapter.notifyDataSetChanged();
    }
    private void updateLoader(boolean b) {
        if (b) {
            progressBarNewJobs.setVisibility(View.VISIBLE);
        } else {
            progressBarNewJobs.setVisibility(View.GONE);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
