package com.konmin.miro.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.konmin.miro.Miro;
import com.konmin.miro.R;
import com.konmin.miro.engine.impl.GlideMediaEngine;
import com.konmin.miro.entity.Album;
import com.konmin.miro.internal.MediaLoader;
import com.konmin.miro.internal.ui.AlbumListFragment;
import com.konmin.miro.internal.ui.MediaListFragment;

import java.util.List;

/**
 * the main interface for user select media;
 *
 * @author Konmin
 * @version create time:2017/11/20
 */

public class MiroActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Album>>, View
        .OnClickListener {


    private Toolbar mToolbar;
    private TextView mTvNoMedia;
    //private FrameLayout mBottomView;
    private TextView mTvAlbumName;
    private TextView mTvUse;
    private TextView mTvPreview;

    private AlbumListFragment mAlbumListFragment;

    private MediaListFragment mMediaListFragment;

    private boolean isShowAlbum;

    private List<Album> mAlbums;

    private void assignViews() {
        mToolbar = findViewById(R.id.toolbar);
        //mFlContainer = findViewById(R.id.fl_container);
        mTvNoMedia = findViewById(R.id.tv_no_media);
        //mBottomView = findViewById(R.id.bottom_view);
        mTvAlbumName = findViewById(R.id.tv_album_name);
        mTvAlbumName.setOnClickListener(this);
        mTvUse = findViewById(R.id.tv_use);
        mTvPreview = findViewById(R.id.tv_preview);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miro);
        assignViews();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.bar_title);
        new Miro.Builder().mediaEngine(new GlideMediaEngine()).all().columnCount(2).build();
        mAlbumListFragment = new AlbumListFragment();
        mMediaListFragment = new MediaListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, mMediaListFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().initLoader(1, null, this);
    }


    @Override
    public Loader<List<Album>> onCreateLoader(int id, Bundle args) {
        return MediaLoader.newInstance(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Album>> loader, final List<Album> data) {
        mAlbums = data;
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                //显示默认的选项
                if (mAlbums != null && !mAlbums.isEmpty()) {
                    mTvNoMedia.setVisibility(View.GONE);
                    mTvAlbumName.setText(mAlbums.get(0).getName());

                } else {
                    mTvNoMedia.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    @Override
    public void onLoaderReset(Loader<List<Album>> loader) {

    }




    @Override
    public void onClick(View v) {
        if (v == mTvAlbumName) {
            if (isShowAlbum) {
                mAlbumListFragment.dismiss();
            } else {
                mAlbumListFragment.show(getSupportFragmentManager(), mAlbums);
            }
            isShowAlbum = !isShowAlbum;
        }
    }
}
