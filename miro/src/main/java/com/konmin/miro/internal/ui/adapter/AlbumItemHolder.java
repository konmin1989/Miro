package com.konmin.miro.internal.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.konmin.miro.R;
import com.konmin.miro.entity.Album;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/23
 */

public class AlbumItemHolder extends RecyclerView.ViewHolder {


    private ImageView mIvCover;
    private TextView mTvAlbumName;
    private TextView mTvCount;
    private RadioButton mRbCurrent;

    private void assignViews() {
        mIvCover = (ImageView) itemView.findViewById(R.id.iv_cover);
        mTvAlbumName = (TextView) itemView.findViewById(R.id.tv_album_name);
        mTvCount = (TextView) itemView.findViewById(R.id.tv_count);
        mRbCurrent = (RadioButton) itemView.findViewById(R.id.rb_current);
    }


    public AlbumItemHolder(View itemView) {
        super(itemView);
        assignViews();
    }


    public void bindData(Album album) {
        mTvCount.setText(album.getMediaItems().size());
        mTvAlbumName.setText(album.getName());

        //SelectionSpec.getInstance().getmMediaEngine().loadThumbnail(t);

    }


   /* private <T extends View> T findViewById(int id) {
        return itemView.findViewById(id);
    }*/

}
