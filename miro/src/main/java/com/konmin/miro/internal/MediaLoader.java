package com.konmin.miro.internal;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.AsyncTaskLoader;

import com.konmin.miro.MimeType;
import com.konmin.miro.R;
import com.konmin.miro.entity.Album;
import com.konmin.miro.entity.MediaItem;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * loading the media from media database the user choose
 *
 * @author Konmin
 * @version create time:2017/11/23
 */

public class MediaLoader extends AsyncTaskLoader<List<Album>> {


    private static final Uri QUERY_URI = MediaStore.Files.getContentUri("external");
    private static final String BUCKET_ORDER_BY = "datetaken DESC";

    private static final String[] PROJECTION = {//
            MediaStore.Files.FileColumns._ID,//
            MediaStore.Files.FileColumns.MIME_TYPE,//
            "bucket_id",//
            "bucket_display_name",//
            MediaStore.MediaColumns.DATA,//
            MediaStore.MediaColumns.SIZE, //
            "duration"//
    };


    private String mSelection;
    private String[] mSelectionArgs;

    private MediaLoader(Context context, String selection, String[] selectionArgs) {
        super(context);
        mSelection = selection;
        mSelectionArgs = selectionArgs;
    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }


    public static MediaLoader newInstance(Context context) {

        Set<MimeType> mimeTypeSet = SelectionSpec.getInstance().getShowMimeType();
        StringBuilder selectionBuilder = new StringBuilder();
        String[] selectionArgs;
        if (mimeTypeSet != null && mimeTypeSet.size() != 0) {
            int mineTypeSize = mimeTypeSet.size();
            selectionArgs = new String[mineTypeSize];
            int i = 0;
            selectionBuilder.append("(");
            for (MimeType mimeType : mimeTypeSet) {
                selectionBuilder.append(MediaStore.Files.FileColumns.MIME_TYPE).append("=? ");
                if (i != mineTypeSize - 1) {
                    selectionBuilder.append(" OR ");
                }
                selectionArgs[i] = mimeType.toString();
                i++;
            }
        } else {
            selectionArgs = new String[2];
            selectionBuilder.append("(");
            selectionBuilder.append(MediaStore.Files.FileColumns.MEDIA_TYPE).append("=? OR ");
            selectionArgs[0] = String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE);
            selectionBuilder.append(MediaStore.Files.FileColumns.MEDIA_TYPE).append("=?");
            selectionArgs[1] = String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO);
        }
        selectionBuilder.append(") AND ").append(MediaStore.MediaColumns.SIZE).append(">=0");
        return new MediaLoader(context, selectionBuilder.toString(), selectionArgs);
    }


    @Override
    public List<Album> loadInBackground() {

        Cursor mediaCursor = getContext().getContentResolver().query(QUERY_URI, PROJECTION, mSelection, mSelectionArgs,
                BUCKET_ORDER_BY);
        if (mediaCursor != null && mediaCursor.getCount() != 0) {
            int idIndex = mediaCursor.getColumnIndex(MediaStore.Files.FileColumns._ID);
            int mimeTypeIndex = mediaCursor.getColumnIndex(MediaStore.Files.FileColumns.MIME_TYPE);
            int bucketIdIndex = mediaCursor.getColumnIndex("bucket_id");
            int bucketDisplayNameIndex = mediaCursor.getColumnIndex("bucket_display_name");
            int dataIndex = mediaCursor.getColumnIndex(MediaStore.MediaColumns.DATA);
            int sizeIndex = mediaCursor.getColumnIndex(MediaStore.MediaColumns.SIZE);
            int durationIndex = mediaCursor.getColumnIndex("duration");
            Map<String, Album> albumMap = new HashMap<>();
            Album allAlbum = new Album();
            allAlbum.setMediaItems(new ArrayList<MediaItem>());
            allAlbum.setName(getContext().getResources().getString(R.string.all));
            while (mediaCursor.moveToNext()) {
                String data = mediaCursor.getString(dataIndex);
                File file = new File(data);
                if (file.exists()) {
                    MediaItem item = new MediaItem();
                    item.setId(mediaCursor.getLong(idIndex));
                    item.setMimeType(mediaCursor.getString(mimeTypeIndex));
                    item.setPath(data);
                    item.setSize(mediaCursor.getLong(sizeIndex));
                    item.setUri(MediaStore.Files.getContentUri(data));
                    item.setDuration(mediaCursor.getLong(durationIndex));
                    String bucketId = mediaCursor.getString(bucketIdIndex);
                    Album album = albumMap.get(bucketId);
                    if (album == null) {
                        album = new Album();
                        album.setCoverPath(data);
                        album.setMediaItems(new ArrayList<MediaItem>());
                        album.setId(bucketId);
                        album.setName(mediaCursor.getString(bucketDisplayNameIndex));
                        albumMap.put(bucketId, album);
                    }
                    allAlbum.getMediaItems().add(item);
                    album.getMediaItems().add(item);
                }
            }

            List<Album> albumList = new ArrayList<>();
            albumList.add(allAlbum);
            for (String s : albumMap.keySet()) {
                albumList.add(albumMap.get(s));
            }
            //allAlbum.setCoverPath(albumList.get(0).getCoverPath());
            return albumList;
        }

        return null;
    }
}
