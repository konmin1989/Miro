package com.konmin.miro.internal;

import android.content.Context;

/**
 * description
 *
 * @author konmin
 * @version create time:2018/2/22.
 */

public class Utils {



    public static boolean checkPermission(Context context,String permission){

        //context.checkSelfPermission()

        try {
            Class clazz = Class.forName("android.support.v4.content.ContextCompat");

           //Method method = clazz.getMethod("checkSelfPermission");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }










        return false;
    }




}
