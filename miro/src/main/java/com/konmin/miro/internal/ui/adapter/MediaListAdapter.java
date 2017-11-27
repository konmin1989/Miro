package com.konmin.miro.internal.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.konmin.miro.internal.Const;
import com.konmin.miro.entity.MediaItem;

import java.util.List;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/21
 */

public class MediaListAdapter extends RecyclerView.Adapter<MediaItemHolder> {


    private List<MediaItem> mMediaItemList;


    @Override
    public MediaItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case Const.VIEW_TYPE_CAPTURE:

                break;
            case Const.VIEW_TYPE_MEDIA:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MediaItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mMediaItemList == null) {
            return 0;
        }
        return mMediaItemList.size();
    }




}
