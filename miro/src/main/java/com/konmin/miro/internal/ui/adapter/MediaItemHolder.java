package com.konmin.miro.internal.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.konmin.miro.entity.MediaItem;
import com.konmin.miro.internal.OnMediaGridClickListener;
import com.konmin.miro.internal.ui.widget.MediaView;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/21
 */

public class MediaItemHolder extends RecyclerView.ViewHolder {


    private MediaView mMediaView;


    public MediaItemHolder(View itemView) {
        super(itemView);
        mMediaView = (MediaView) itemView;
    }

    public void setData(MediaItem item, int size) {
        mMediaView.setMedia(item, size);
    }

    public void setOnMediaGridClickListener(OnMediaGridClickListener listener) {
        mMediaView.setMediaGridClickListener(listener);
    }
}
