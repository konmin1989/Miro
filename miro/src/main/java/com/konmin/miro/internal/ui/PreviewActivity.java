package com.konmin.miro.internal.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.konmin.miro.R;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2018/1/11
 */

public class PreviewActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mFlContainer;
    private FrameLayout mBottomView;
    private TextView mTvBack;
    private TextView mTvUse;

    private void assignViews() {
        mFlContainer = (ViewPager) findViewById(R.id.fl_container);
        mBottomView = (FrameLayout) findViewById(R.id.bottom_view);
        mTvBack = (TextView) findViewById(R.id.tv_back);
        mTvBack.setOnClickListener(this);
        mTvUse = (TextView) findViewById(R.id.tv_use);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        assignViews();
    }


    @Override
    public void onClick(View v) {
        if (v == mTvBack) {
            this.finish();
        }
    }
}
