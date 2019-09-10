package com.asaptemp.provider.job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asaptemp.provider.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private Context context;
    private ArrayList<JobsModel> jobsModelArrayList;

    JobsAdapter(Context context, ArrayList<JobsModel> jobsModelArrayList, OnItemClickListener listener) {
        this.context = context;
        this.jobsModelArrayList = jobsModelArrayList;
        this.listener = listener;
    }



    public void clearList() {
        jobsModelArrayList.clear();
        notifyDataSetChanged();
    }

    public void addList(ArrayList<JobsModel> jobsModelArrayList) {
        this.jobsModelArrayList = jobsModelArrayList;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_new_jobs, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JobsModel jobsModel = jobsModelArrayList.get(position);
//
//
//        holder.txtUserName.setText(jobsModel.getUserName());
//        holder.txtUserTimeAgo.setText(jobsModel.getTimeAgo());
//        holder.txtServiceName.setText(jobsModel.getServiceName());
//        holder.txtJobDescription.setText(jobsModel.getDescription());
//        holder.txtJobDate.setText(jobsModel.getJobDate());
//        holder.txtApproxTime.setText(jobsModel.getJobTime());
//        holder.txtJobAmount.setText(jobsModel.getJobPrice());
//        holder.txtJobDistance.setText(jobsModel.getJobDistance());
//        holder.txtJobAddress.setText(jobsModel.getJobAddress());
//
//
//        if(jobsModel.getJobId() == 0){
//            Drawable drawable = context.getDrawable(R.drawable.bg_rounded_rect_orange_white);
//            holder.linearMainJobs.setBackground(drawable);
//        }else{
//            Drawable drawable = context.getDrawable(R.drawable.bg_rounded_rect_white_with_shadow);
//            holder.linearMainJobs.setBackground(drawable);
//        }

    }

    @Override
    public int getItemCount() {
        return jobsModelArrayList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.linearMainJobs)
//        LinearLayout linearMainJobs;
//
//        @BindView(R.id.linearUserInfo)
//        LinearLayout linearUserInfo;
//
//        @BindView(R.id.linearJobDescription)
//        LinearLayout linearJobDescription;
//
//        @BindView(R.id.linearDateAndTimeJobs)
//        LinearLayout linearDateAndTimeJobs;
//
//        @BindView(R.id.linearJobAddress)
//        LinearLayout linearJobAddress;
//
//        @BindView(R.id.txtUserName)
//        CustomTextView txtUserName;
//
//        @BindView(R.id.txtUserTimeAgo)
//        CustomTextView txtUserTimeAgo;
//
//        @BindView(R.id.txtServiceName)
//        CustomTextView txtServiceName;
//
//        @BindView(R.id.txtJobDescription)
//        CustomTextView txtJobDescription;
//
//        @BindView(R.id.txtJobDate)
//        CustomTextView txtJobDate;
//
//
//        @BindView(R.id.txtApproxTime)
//        CustomTextView txtApproxTime;
//
//        @BindView(R.id.txtJobAmount)
//        CustomTextView txtJobAmount;
//
//        @BindView(R.id.txtJobDistance)
//        CustomTextView txtJobDistance;
//
//        @BindView(R.id.txtJobAddress)
//        CustomTextView txtJobAddress;

        ViewHolder(@NonNull View itemView, final OnItemClickListener mListener) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    if (mListener != null) {
                        mListener.onItemClick(pos);
                    }
                }

            });

        }
    }
}
