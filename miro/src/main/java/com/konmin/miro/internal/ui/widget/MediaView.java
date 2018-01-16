package com.konmin.miro.internal.ui.widget;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.konmin.miro.MimeType;
import com.konmin.miro.R;
import com.konmin.miro.entity.MediaItem;
import com.konmin.miro.internal.OnMediaGridClickListener;
import com.konmin.miro.internal.SelectionSpec;
import com.konmin.miro.internal.ui.adapter.MediaItemHolder;

import java.io.File;

/**
 * @author Konmin
 * @version create time:2017/11/23
 */

public class MediaView extends SquareLayout implements View.OnClickListener {


    private ImageView mIvThumbnail;
    private ImageView mIvMediaType;
    private TextView mTvVideoLength;
    private CheckView mCvSelect;
    private MediaItem mMediaItem;
    private MediaItemHolder mMediaItemHolder;

    private OnMediaGridClickListener mMediaGridClickListener;

    public MediaView(@NonNull Context context) {
        this(context, null);
    }

    public MediaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_media, this, true);
        mIvThumbnail = findViewById(R.id.iv_thumbnail);
        mIvMediaType = findViewById(R.id.iv_media_type);
        mTvVideoLength = findViewById(R.id.tv_video_length);
        mCvSelect = findViewById(R.id.cv_select);
        mCvSelect.setOnClickListener(this);
        mIvThumbnail.setOnClickListener(this);
    }


    public void setMediaItemHolder(MediaItemHolder holder){
        this.mMediaItemHolder = holder;
    }

    public void setMedia(MediaItem mediaItem, int reSize) {
        mMediaItem = mediaItem;
        setGif();
        setImage(reSize);
        setVideoLength();
    }


    private void setGif() {
        if (mMediaItem.isGif()) {
            mIvMediaType.setVisibility(VISIBLE);
        } else {
            mIvMediaType.setVisibility(GONE);
        }
    }


    private void setImage(int reSize) {

        if (mMediaItem.isGif()) {
            SelectionSpec.getInstance().getMediaEngine().loadGifThumbnail(getContext(), reSize, null, mIvThumbnail, Uri
                    .fromFile(new File(mMediaItem.getPath())));
        } else {
            SelectionSpec.getInstance().getMediaEngine().loadThumbnail(getContext(), reSize, null, mIvThumbnail, Uri.fromFile
                    (new File(mMediaItem.getPath())));
        }

    }

    private void setVideoLength() {
        if (mMediaItem.isVideo()) {
            mTvVideoLength.setVisibility(VISIBLE);
            mTvVideoLength.setText(DateUtils.formatElapsedTime(mMediaItem.getDuration() / 1000));
        } else {
            mTvVideoLength.setVisibility(GONE);
        }
    }


    @Override
    public void onClick(View v) {
        if (v == mIvThumbnail) {
            if (mMediaGridClickListener != null) {
                mMediaGridClickListener.onMediaClick(mMediaItem,mMediaItemHolder);
            }
        }
        if (v == mCvSelect) {
            boolean checked = !mCvSelect.getCheck();
            mCvSelect.setChecked(checked);
            mMediaItem.setCheck(checked);
            if (mMediaGridClickListener != null) {
                mMediaGridClickListener.onCheck(mMediaItem,mMediaItemHolder);
            }
        }
    }


    public void setMediaGridClickListener(OnMediaGridClickListener listener) {
        this.mMediaGridClickListener = listener;
    }
}
