package com.konmin.miro.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * the Album form media database
 *
 * @author Konmin
 * @version create time:2017/11/20
 */

public class Album implements Parcelable {

    private String id;
    private String coverPath;
    private String name;
    private long count;

    private List<MediaItem> mediaItems;


    public Album() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<MediaItem> getMediaItems() {
        return mediaItems;
    }

    public void setMediaItems(List<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
    }

    protected Album(Parcel in) {
        id = in.readString();
        coverPath = in.readString();
        name = in.readString();
        count = in.readLong();
        mediaItems = in.createTypedArrayList(MediaItem.CREATOR);
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
        dest.writeTypedList(mediaItems);
    }
}
