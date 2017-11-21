package com.konmin.miro;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Describe
 *
 * @author Konmin
 * @version create time:2017/11/21
 */

public enum MediaType {

    ///////////////////////////////images///////////////////////////////////////
    JPEG("image/jpeg", arraySetOf("jpg", "jpeg")),                   //
    PNG("image/png", arraySetOf("png")),                             //
    GIF("image/gif", arraySetOf("gif")),                             //
    BMP("image/x-ms-bmp", arraySetOf("bmp")),                        //
    WEBP("image/webp", arraySetOf("webp")),                          //
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////videos///////////////////////////////////////
    MPEG("video/mpeg", arraySetOf("mpeg", "mpg")),                   //
    MP4("video/mp4", arraySetOf("mp4", "m4v")),                      //
    QUICKTIME("video/quicktime", arraySetOf("mov")),                 //
    THREEGPP("video/3gpp", arraySetOf("3gp", "3gpp")),               //
    THREEGPP2("video/3gpp2", arraySetOf("3g2", "3gpp2")),            //
    MKV("video/x-matroska", arraySetOf("mkv")),                      //
    WEBM("video/webm", arraySetOf("webm")),                          //
    TS("video/mp2ts", arraySetOf("ts")),                             //
    AVI("video/avi", arraySetOf("avi"));                             //
    ///////////////////////////////videos//////////////////////////////////////


    private String mMimeTypeName;
    private Set<String> mSuffix;

    MediaType(String mimeTypeName, Set<String> suffix) {
        mMimeTypeName = mimeTypeName;
        mSuffix = suffix;
    }

    private static Set<String> arraySetOf(String... suffixes) {
        return new HashSet<>(Arrays.asList(suffixes));
    }



    @Override
    public String toString() {
        return mMimeTypeName;
    }
}
