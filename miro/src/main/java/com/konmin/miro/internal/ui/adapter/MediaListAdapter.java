package com.konmin.miro.internal.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.konmin.miro.R;
import com.konmin.miro.entity.Album;
import com.konmin.miro.internal.Const;
import com.konmin.miro.entity.MediaItem;
import com.konmin.miro.internal.SelectionSpec;

import java.util.List;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/21
 */

public class MediaListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<MediaItem> mMediaItemList;

    private RecyclerView mRecyclerView;
    private int mImageResize;

    public MediaListAdapter(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }


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
                View mediaView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_media, parent, false);
                return new MediaItemHolder(mediaView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof MediaItemHolder) {
            MediaItemHolder mediaItemHolder = (MediaItemHolder) holder;
            mediaItemHolder.setData(mMediaItemList.get(position), getImageResize(holder.itemView.getContext()));
        }

    }


    private int getImageResize(Context context) {
        if (mImageResize == 0) {
            RecyclerView.LayoutManager lm = mRecyclerView.getLayoutManager();
            int spanCount = ((GridLayoutManager) lm).getSpanCount();
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            int availableWidth = screenWidth - context.getResources().getDimensionPixelSize(R.dimen.grid_spacing) *
                    (spanCount - 1);
            mImageResize = availableWidth / spanCount;
            mImageResize = (int) (mImageResize * SelectionSpec.getInstance().getThumbnailScale());
        }
        return mImageResize;
    }


    @Override
    public int getItemViewType(int position) {

        return Const.VIEW_TYPE_MEDIA;
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
