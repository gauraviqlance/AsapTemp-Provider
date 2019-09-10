package com.iqlance.asaptemp.provider.job;

import android.os.Parcel;
import android.os.Parcelable;

public class JobsModel implements Parcelable {

    int jobId;
    String userName;
    String timeAgo;
    String serviceName;
    String description;
    String jobDate;
    String jobTime;
    String jobPrice;
    String jobDistance;
    String jobAddress;

    public JobsModel() {
    }

    public JobsModel(int jobId, String userName, String timeAgo, String serviceName, String description, String jobDate, String jobTime, String jobPrice, String jobDistance, String jobAddress) {
        this.jobId = jobId;
        this.userName = userName;
        this.timeAgo = timeAgo;
        this.serviceName = serviceName;
        this.description = description;
        this.jobDate = jobDate;
        this.jobTime = jobTime;
        this.jobPrice = jobPrice;
        this.jobDistance = jobDistance;
        this.jobAddress = jobAddress;
    }

    protected JobsModel(Parcel in) {
        jobId = in.readInt();
        userName = in.readString();
        timeAgo = in.readString();
        serviceName = in.readString();
        description = in.readString();
        jobDate = in.readString();
        jobTime = in.readString();
        jobPrice = in.readString();
        jobDistance = in.readString();
        jobAddress = in.readString();
    }

    public static final Creator<JobsModel> CREATOR = new Creator<JobsModel>() {
        @Override
        public JobsModel createFromParcel(Parcel in) {
            return new JobsModel(in);
        }

        @Override
        public JobsModel[] newArray(int size) {
            return new JobsModel[size];
        }
    };

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobDate() {
        return jobDate;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public String getJobTime() {
        return jobTime;
    }

    public void setJobTime(String jobTime) {
        this.jobTime = jobTime;
    }

    public String getJobPrice() {
        return jobPrice;
    }

    public void setJobPrice(String jobPrice) {
        this.jobPrice = jobPrice;
    }

    public String getJobDistance() {
        return jobDistance;
    }

    public void setJobDistance(String jobDistance) {
        this.jobDistance = jobDistance;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(jobId);
        dest.writeString(userName);
        dest.writeString(timeAgo);
        dest.writeString(serviceName);
        dest.writeString(description);
        dest.writeString(jobDate);
        dest.writeString(jobTime);
        dest.writeString(jobPrice);
        dest.writeString(jobDistance);
        dest.writeString(jobAddress);
    }
}
