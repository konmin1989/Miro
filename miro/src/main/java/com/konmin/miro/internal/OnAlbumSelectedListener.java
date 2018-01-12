package com.konmin.miro.internal;

import com.konmin.miro.entity.Album;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2018/1/5
 */

public interface OnAlbumSelectedListener {

    void onAlbumSelected(Album album);

    void onAlbumCancel();
}
