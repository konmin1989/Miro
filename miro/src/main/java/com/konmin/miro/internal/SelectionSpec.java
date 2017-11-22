package com.konmin.miro.internal;

import com.konmin.miro.MimeType;

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

    public Set<MimeType> mShowMimeType;

    public void setShowMimeType(Set<MimeType> mShowMimeType) {
        this.mShowMimeType = mShowMimeType;
    }


}
