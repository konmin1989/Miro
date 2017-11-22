package com.konmin.miro;

import android.support.annotation.IntDef;

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

        public Builder showOnlyImage() {
            SelectionSpec.getInstance().setShowMimeType(MimeType.ofImage());
            return this;
        }

        public Builder showOnlyVideo() {
            SelectionSpec.getInstance().setShowMimeType(MimeType.ofVideo());
            return this;
        }

        public Builder showAll() {
            SelectionSpec.getInstance().setShowMimeType(MimeType.ofAll());
            return this;
        }

        public Builder showMimeType(MimeType mimeType, MimeType... rest) {
            SelectionSpec.getInstance().setShowMimeType(MimeType.of(mimeType, rest));
            return this;
        }


        public Miro build() {
            return new Miro();
        }
    }

}
