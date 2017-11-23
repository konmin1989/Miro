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
import com.konmin.miro.internal.ui.adapter.AlbumListAdapter;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/21
 */

public class MediaListFragment extends Fragment {


    private RecyclerView mRecyclerView;

    private AlbumListAdapter mAlbumListAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_media_list, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_media_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAlbumListAdapter = new AlbumListAdapter() {
            @Override
            public void onAlbumSelected(Album album) {

            }
        };
        mRecyclerView.setAdapter(mAlbumListAdapter);
    }
}
