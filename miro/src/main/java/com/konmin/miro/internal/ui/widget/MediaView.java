package com.konmin.miro.internal.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.konmin.miro.R;

/**
 * @author Konmin
 * @version create time:2017/11/23
 */

public class MediaView extends FrameLayout implements View.OnClickListener {


    private ImageView mIvThumbnail;
    private ImageView mIvMediaType;
    private TextView mTvVideoLength;
    private CheckView mCvSelect;


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


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }


    @Override
    public void onClick(View v) {

        if (v == mIvThumbnail) {
            //goto preview;
        }
        if (v == mCvSelect) {
            mCvSelect.setChecked(!mCvSelect.getCheck());
        }
    }
}
