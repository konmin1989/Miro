package com.konmin.miro.engine.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.konmin.miro.engine.MediaEngine;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/24
 */

public class GlideMediaEngine implements MediaEngine {


    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        Glide.with(context).load(uri).asBitmap().placeholder(placeholder).override(resize, resize).centerCrop().into(imageView);
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(context).load(uri).override(resizeX, resizeY).priority(Priority.HIGH).into(imageView);
    }

    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(context).load(uri).asGif().override(resizeX, resizeY).priority(Priority.HIGH).into(imageView);
    }
}
