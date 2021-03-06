package com.konmin.miro.ui;

import android.Manifest;
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
import android.widget.Toast;

import com.konmin.miro.MimeType;
import com.konmin.miro.Miro;
import com.konmin.miro.R;
import com.konmin.miro.engine.impl.GlideMediaEngine;
import com.konmin.miro.entity.Album;
import com.konmin.miro.internal.MediaLoader;
import com.konmin.miro.internal.OnAlbumSelectedListener;
import com.konmin.miro.internal.PermissionUtils;
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
        .OnClickListener, OnAlbumSelectedListener {


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
        if (PermissionUtils.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            init();
        } else {
            PermissionUtils.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionUtils
                    .PermissionListener() {
                @Override
                public void onPermissionResult(boolean grantedResult) {
                    if (grantedResult) {
                        init();
                    } else {
                        Toast.makeText(MiroActivity.this, "没有读写文件的权限，请设置允许", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void init() {

        setContentView(R.layout.activity_miro);
        assignViews();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.bar_title);
        new Miro.Builder().mediaEngine(new GlideMediaEngine()).all().columnCount(3).build();
        mAlbumListFragment = new AlbumListFragment();
        mAlbumListFragment.setAlbumSelectedListener(this);
        mMediaListFragment = new MediaListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, mMediaListFragment).commit();
        getSupportLoaderManager().initLoader(1, null, this);
    }


    @Override
    protected void onResume() {
        super.onResume();

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
                    mMediaListFragment.setAlbum(mAlbums.get(0));
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

    @Override
    public void onAlbumSelected(Album album) {
        mMediaListFragment.setAlbum(album);
        isShowAlbum = false;
        mTvAlbumName.setText(album.getName());
    }

    @Override
    public void onAlbumCancel() {
        isShowAlbum = false;
    }
}
