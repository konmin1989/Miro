package com.konmin.miro.internal;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;

import com.konmin.miro.entity.Album;

import java.util.List;

/**
 * load the album for show
 *
 * @author Konmin
 * @version create time:2017/11/21
 */

public class AlbumLoader extends AsyncTaskLoader<List<Album>> {

    public static final String COLUMN_COUNT = "count";
    private static final Uri QUERY_URI = MediaStore.Files.getContentUri("external");


    /***
     *  private final String id;
     private final String coverPath;
     private final String name;
     private long count;
     *
     *
     *
     *
     */
    private static final String[] PROJECTION = {//
            MediaStore.MediaColumns.DATA,//
            MediaStore.MediaColumns.DISPLAY_NAME,//
            MediaStore.MediaColumns.SIZE, //
            "COUNT (*) AS" + COLUMN_COUNT//
    };


    public static AlbumLoader newInstance(Context context) {


        return new AlbumLoader(context);

    }


    private AlbumLoader(Context context) {
        super(context);
    }


    @Override
    public List<Album> loadInBackground() {

        getContext().getContentResolver().query(QUERY_URI, ) return null;
    }
}
