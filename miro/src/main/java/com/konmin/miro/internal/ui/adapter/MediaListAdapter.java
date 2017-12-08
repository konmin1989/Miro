package com.konmin.miro.internal.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.konmin.miro.R;
import com.konmin.miro.entity.Album;
import com.konmin.miro.internal.Const;
import com.konmin.miro.entity.MediaItem;

import java.util.List;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/21
 */

public class MediaListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<MediaItem> mMediaItemList;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Const.VIEW_TYPE_CAPTURE:
                View captureView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_capture, parent, false);
                captureView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                return new CaptureHolder(captureView);
            case Const.VIEW_TYPE_MEDIA:
                View mediaView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media, parent, false);
                return new MediaItemHolder(mediaView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        /*if (holder instanceof CaptureHolder) {

        } else {


        }*/
    }


    public void setAlbum(Album album) {

        if (album != null) {
            mMediaItemList = album.getMediaItems();
            notifyDataSetChanged();
        }
    }


    @Override
    public int getItemCount() {
        if (mMediaItemList == null) {
            return 0;
        }
        return mMediaItemList.size();
    }
}
