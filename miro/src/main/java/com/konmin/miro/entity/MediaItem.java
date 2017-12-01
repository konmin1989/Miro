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

public class MediaItem implements Parcelable {

    private long id;
    private String mimeType;
    private Uri uri;
    private String path;
    private long size;
    private long duration; // only video has this params


    public MediaItem() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

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
