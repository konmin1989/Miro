package com.konmin.miro.internal.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.konmin.miro.R;
import com.konmin.miro.entity.Album;

import java.util.List;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/23
 */

public abstract class AlbumListAdapter extends RecyclerView.Adapter<AlbumItemHolder> implements View.OnClickListener {


    private List<Album> mAlbums;


    @Override
    public AlbumItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        AlbumItemHolder holder = new AlbumItemHolder(view);
        view.setOnClickListener(this);
        view.setTag(holder);
        return holder;
    }


    @Override
    public void onBindViewHolder(AlbumItemHolder holder, int position) {
        holder.bindData(mAlbums.get(position));
    }

    @Override
    public int getItemCount() {
        if (mAlbums == null) {
            return 0;
        }
        return mAlbums.size();
    }

    @Override
    public void onClick(View v) {
        AlbumItemHolder holder = (AlbumItemHolder) v.getTag();
        int postion = holder.getAdapterPosition();
        onAlbumSelected(mAlbums.get(postion));
    }

    public abstract void onAlbumSelected(Album album);


    public void setAlbums(List<Album> albums) {
        mAlbums = albums;
    }

}
