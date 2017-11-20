package com.konmin.miro.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * the Album form media database
 *
 * @author Konmin
 * @version create time:2017/11/20
 */

public class Album implements Parcelable {

    private final String id;
    private final String coverPath;
    private final String name;
    private long count;


    protected Album(Parcel in) {
        id = in.readString();
        coverPath = in.readString();
        name = in.readString();
        count = in.readLong();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(coverPath);
        dest.writeString(name);
        dest.writeLong(count);
    }
}
