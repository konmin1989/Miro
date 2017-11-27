package com.konmin.miro;

import android.support.annotation.IntDef;

import com.konmin.miro.engine.MediaEngine;
import com.konmin.miro.internal.SelectionSpec;

/**
 * Entry for Miro's media picker
 *
 * @author Konmin
 * @version create time:2017/11/20
 */

public class Miro {


    private Builder mBuilder;

    private Miro() {
    }

    public static class Builder {

        private SelectionSpec mSelectionSpec = SelectionSpec.getInstance();

        public Builder onlyImage() {
            mSelectionSpec.setShowMimeType(MimeType.ofImage());
            return this;
        }

        public Builder onlyVideo() {
            mSelectionSpec.setShowMimeType(MimeType.ofVideo());
            return this;
        }

        public Builder all() {
            mSelectionSpec.setShowMimeType(MimeType.ofAll());
            return this;
        }

        public Builder mimeType(MimeType mimeType, MimeType... rest) {
            mSelectionSpec.setShowMimeType(MimeType.of(mimeType, rest));
            return this;
        }


        public Builder columnCount(int columnCount) {

            if (columnCount <= 0) {
                throw new IllegalArgumentException("the column count must greater than zero ");
            }
            mSelectionSpec.setColumnCount(columnCount);
            return this;
        }


        public Builder mediaEngine(MediaEngine mediaEngine) {
            mSelectionSpec.setMediaEngine(mediaEngine);
            return this;
        }


        public Miro build() {
            return new Miro();
        }
    }

}
