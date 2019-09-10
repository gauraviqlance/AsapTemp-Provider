package com.asaptemp.provider.job;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.asaptemp.provider.R;
import com.asaptemp.provider.utils.CustomTextView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobFragment  extends Fragment{
    private Context context;
    private View view;

    @BindView(R.id.toolbarMyJobs)
    Toolbar toolbarMyJobs;

    @BindView(R.id.tabsMyJobs)
    TabLayout tabsMyJobs;
    @BindView(R.id.viewPagerMyJobs)
    ViewPager viewPagerMyJobs;


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
        view = inflater.inflate(R.layout.fragment_jobs, container, false);
        ButterKnife.bind(this,view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //toolbar = view.findViewById(R.id.toolbarMain);


        //For disabling swiping
        viewPagerMyJobs.beginFakeDrag();
        viewPagerMyJobs.setSaveFromParentEnabled(false);
        //For enable swiping
        //
        //if (mViewPager.isFakeDragging())
        //     mViewPager.endFakeDrag();


        setupViewPager(viewPagerMyJobs);
        tabsMyJobs.setupWithViewPager(viewPagerMyJobs);
        setupTab();
    }

    private void setupTab() {
        CustomTextView tabOne = (CustomTextView) LayoutInflater.from(context).inflate(R.layout.layout_custom_tab, null);
        tabOne.setText(context.getResources().getString(R.string.title_new));
        Objects.requireNonNull(tabsMyJobs.getTabAt(0)).setCustomView(tabOne);

        CustomTextView tabTwo = (CustomTextView) LayoutInflater.from(context).inflate(R.layout.layout_custom_tab, null);
        tabTwo.setText(context.getResources().getString(R.string.ongoing));
        Objects.requireNonNull(tabsMyJobs.getTabAt(1)).setCustomView(tabTwo);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
        adapter.addFragment(new NewJobsFragment(), context.getResources().getString(R.string.title_new));
        adapter.addFragment(new OngoingJobsFragment(), context.getResources().getString(R.string.ongoing));
        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            //noinspection deprecation
            super(manager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
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
