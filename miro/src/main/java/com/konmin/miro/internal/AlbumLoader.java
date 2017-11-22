package com.konmin.miro.internal;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.konmin.miro.Miro;
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


    private static final String[] PROJECTION = {//
            "bucket_id",//
            "bucket_display_name",//
            MediaStore.MediaColumns.DATA,//
            MediaStore.MediaColumns.SIZE, //
            "COUNT(*) AS" + COLUMN_COUNT//
    };

    //------------------------  media---------------------------------------------
    private static final String SELECTION = "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?" + " OR " + MediaStore.Files
            .FileColumns.MEDIA_TYPE + "=?)" + " AND " + MediaStore.MediaColumns.SIZE + ">0" + ") GROUP BY bucket_id";

    //--------------------------single media type---------------------------------------

    private static final String[] SELECTION_ARGS = {};


    public static AlbumLoader newInstance(Context context) {

        //判断用户到底选了啥？然后再根据媒体类型从数据库查询






        return new AlbumLoader(context);

    }


    private AlbumLoader(Context context) {
        super(context);

    }


    @Override
    public List<Album> loadInBackground() {

        Cursor cursor = getContext().getContentResolver().query(QUERY_URI, PROJECTION, SELECTION, SELECTION_ARGS, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        do {
            int i = cursor.getInt(1);
        } while (cursor.moveToNext());


        return null;
    }
}
