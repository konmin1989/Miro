package com.konmin.miro.internal.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.konmin.miro.R;
import com.konmin.miro.entity.Album;
import com.konmin.miro.internal.GridItemDecoration;
import com.konmin.miro.internal.SelectionSpec;
import com.konmin.miro.internal.ui.adapter.AlbumListAdapter;
import com.konmin.miro.internal.ui.adapter.MediaListAdapter;

import java.util.List;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/21
 */

public class MediaListFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private MediaListAdapter mMediaListAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_media_list, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_media_list);
        final int columnCount = SelectionSpec.getInstance().getColumnCount();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnCount));
        mRecyclerView.setHasFixedSize(true);
        int spacing = getContext().getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        mRecyclerView.addItemDecoration(new GridItemDecoration(columnCount, false, spacing));
        mMediaListAdapter = new MediaListAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mMediaListAdapter);
    }


    public void setAlbum(Album album) {
        mMediaListAdapter.setAlbum(album);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
