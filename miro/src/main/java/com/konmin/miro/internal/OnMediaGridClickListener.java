package com.konmin.miro.internal;

import com.konmin.miro.entity.MediaItem;
import com.konmin.miro.internal.ui.adapter.MediaItemHolder;
import com.konmin.miro.internal.ui.widget.MediaView;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2018/1/12
 */

public interface OnMediaGridClickListener {

    void onMediaClick(MediaItem mediaItem,MediaItemHolder holder);

    void onCheck(MediaItem mediaItem,MediaItemHolder holder);
}
