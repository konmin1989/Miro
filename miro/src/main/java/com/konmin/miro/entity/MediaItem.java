package com.konmin.miro.entity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/20
 */

public class MediaItem  implements Parcelable{

    private final long id;
    private final String mimeType;
    private final Uri uri;
    private final String path;
    private final long size;
    private final long duration; // only for video, in ms



    protected MediaItem(Parcel in) {
        id = in.readLong();
        mimeType = in.readString();
        uri = in.readParcelable(Uri.class.getClassLoader());
        path = in.readString();
        size = in.readLong();
        duration = in.readLong();
    }

    public static final Creator<MediaItem> CREATOR = new Creator<MediaItem>() {
        @Override
        public MediaItem createFromParcel(Parcel in) {
            return new MediaItem(in);
        }

        @Override
        public MediaItem[] newArray(int size) {
            return new MediaItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(mimeType);
        dest.writeParcelable(uri, flags);
        dest.writeString(path);
        dest.writeLong(size);
        dest.writeLong(duration);
    }
}
