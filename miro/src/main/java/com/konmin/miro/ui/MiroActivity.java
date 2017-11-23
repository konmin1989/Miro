package com.konmin.miro.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.konmin.miro.Miro;
import com.konmin.miro.R;
import com.konmin.miro.entity.Album;
import com.konmin.miro.internal.MediaLoader;

import java.util.List;

/**
 * the main interface for user select media;
 *
 * @author Konmin
 * @version create time:2017/11/20
 */

public class MiroActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Album>> {


    private Toolbar mToolbar;
    private FrameLayout mFlContainer;
    private TextView mTvNoMedia;
    private FrameLayout mBottomView;
    private TextView mTvAlbumName;
    private TextView mTvUse;
    private TextView mTvPreview;

    private void assignViews() {
        mToolbar = findViewById(R.id.toolbar);
        mFlContainer = findViewById(R.id.fl_container);
        mTvNoMedia = findViewById(R.id.tv_no_media);
        mBottomView = findViewById(R.id.bottom_view);
        mTvAlbumName = findViewById(R.id.tv_album_name);
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

        new Miro.Builder().showOnlyVideo().build();
        getSupportLoaderManager().initLoader(1, null, this);
    }


    @Override
    public Loader<List<Album>> onCreateLoader(int id, Bundle args) {
        return MediaLoader.newInstance(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Album>> loader, List<Album> data) {

        if (data != null) {
            Log.e("TAS",data.size()+"");
        }else{
            Log.e("TAG","DATA is null");
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Album>> loader) {

    }
}
