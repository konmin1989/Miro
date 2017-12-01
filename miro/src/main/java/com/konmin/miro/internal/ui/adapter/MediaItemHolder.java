package com.konmin.miro.internal.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.konmin.miro.R;
import com.konmin.miro.entity.MediaItem;
import com.konmin.miro.internal.ui.widget.CheckView;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/21
 */

public class MediaItemHolder extends RecyclerView.ViewHolder {


    private ImageView ivThumbnail;
    private ImageView ivMediaType;
    private TextView tvVideoLength;
    private CheckView mCvSelect;

    public MediaItemHolder(View itemView) {
        super(itemView);
        ivThumbnail = itemView.findViewById(R.id.iv_thumbnail);
        ivMediaType = itemView.findViewById(R.id.iv_media_type);
        tvVideoLength = itemView.findViewById(R.id.tv_video_length);
        mCvSelect = itemView.findViewById(R.id.cv_select);
    }


    public void setData(MediaItem item){

    }



}
