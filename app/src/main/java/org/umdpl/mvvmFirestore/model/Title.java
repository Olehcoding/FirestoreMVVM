package org.umdpl.mvvmFirestore.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Title implements Parcelable {

    private int titleOrder;
    private String title;

    public Title() {
    }

    public Title( int titleOrder, String title) {
        this.titleOrder = titleOrder;
        this.title = title;
    }

    protected Title(Parcel in) {
        titleOrder = in.readInt();
        title = in.readString();
    }



    public int getTitleOrder() {
        return titleOrder;
    }

    public void setTitleOrder(int titleOrder) {
        this.titleOrder = titleOrder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static final Creator<Title> CREATOR = new Creator<Title>() {
        @Override
        public Title createFromParcel(Parcel in) {
            return new Title(in);
        }

        @Override
        public Title[] newArray(int size) {
            return new Title[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(titleOrder);
        dest.writeString(title);
    }

    @NonNull
    @Override
    public String toString() {
        return "Title{" +
                "dataID='"  + '\'' +
                ", titleOrder=" + titleOrder +
                ", title='" + title + '\'' +
                '}';
    }
}