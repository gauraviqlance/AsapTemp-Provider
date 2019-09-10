package com.asaptemp.provider.tour;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class TourModel extends BaseObservable implements Parcelable {
    private int pic;
    private String title;
    private String msg;

    public TourModel() {
    }

    public TourModel(int pic, String title, String msg) {
        this.pic = pic;
        this.title = title;
        this.msg = msg;
    }

    protected TourModel(Parcel in) {
        pic = in.readInt();
        title = in.readString();
        msg = in.readString();
    }

    public static final Creator<TourModel> CREATOR = new Creator<TourModel>() {
        @Override
        public TourModel createFromParcel(Parcel in) {
            return new TourModel(in);
        }

        @Override
        public TourModel[] newArray(int size) {
            return new TourModel[size];
        }
    };

    @Bindable
    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
        notifyPropertyChanged(com.asaptemp.provider.BR.pic);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
        notifyPropertyChanged(com.asaptemp.provider.BR.title);
    }

    @Bindable
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyPropertyChanged(com.asaptemp.provider.BR.msg);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(pic);
        parcel.writeString(title);
        parcel.writeString(msg);
    }
}
