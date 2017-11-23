package com.konmin.miro.internal;

import com.konmin.miro.MimeType;
import com.konmin.miro.engine.MediaEngine;

import java.util.Set;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/22
 */

public final class SelectionSpec {


    private static SelectionSpec mSelectionSpec = new SelectionSpec();

    public static SelectionSpec getInstance() {
        return mSelectionSpec;
    }

    private Set<MimeType> mShowMimeType;

    private int mColumnCount;

    private MediaEngine mMediaEngine;


    public static SelectionSpec getSelectionSpec() {
        return mSelectionSpec;
    }

    public static void setSelectionSpec(SelectionSpec selectionSpec) {
        SelectionSpec.mSelectionSpec = mSelectionSpec;
    }

    public Set<MimeType> getShowMimeType() {
        return mShowMimeType;
    }

    public void setShowMimeType(Set<MimeType> showMimeType) {
        this.mShowMimeType = showMimeType;
    }

    public int getColumnCount() {
        return mColumnCount;
    }

    public void setColumnCount(int columnCount) {
        this.mColumnCount = columnCount;
    }

    public MediaEngine getmMediaEngine() {
        return mMediaEngine;
    }

    public void setmMediaEngine(MediaEngine mediaEngine) {
        this.mMediaEngine = mediaEngine;
    }
}
