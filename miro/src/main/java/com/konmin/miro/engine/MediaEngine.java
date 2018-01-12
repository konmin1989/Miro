package com.konmin.miro.engine;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/23
 */

public interface MediaEngine {

    /**
     * @param context
     * @param resize
     * @param placeholder
     * @param imageView
     * @param uri
     */
    void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri);

    /**
     * @param context
     * @param resizeX
     * @param resizeY
     * @param imageView
     * @param uri
     */
    void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri);

    /**
     * @param context
     * @param resizeX
     * @param resizeY
     * @param imageView
     * @param uri
     */
    void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri);


    /**
     * @param context
     * @param resize
     * @param placeholder
     * @param imageView
     * @param uri
     */
    void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri);
}
