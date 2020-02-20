package com.konmin.miro.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * description checked the permission
 *
 * @author konmin
 * @version create time:2018/2/22.
 */

public class PermissionUtils {


    public static final int REQUEST_CODE = 110;


    public static boolean checkPermission(Context context, String permission) {

        if (context == null) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                Class contextClass = Class.forName("android.content.Context");
                Method checkSelfPermissionMethod = contextClass.getMethod("checkSelfPermission", String.class);
                int grantedResult = (int) checkSelfPermissionMethod.invoke(context, permission);
                return grantedResult == PackageManager.PERMISSION_GRANTED;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
        }
    }


    public static void requestPermission(AppCompatActivity activity, String permission, PermissionListener listener) {
        if (listener == null) {
            Log.e("TAG", "the permissionListener is null");
            return;
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        PermissionFragment fragment = (PermissionFragment) fragmentManager.findFragmentByTag("PermissionFragment");
        if (fragment == null) {
            fragment = new PermissionFragment();
            fragmentManager.beginTransaction().add(fragment, "PermissionFragment").commit();
            fragmentManager.executePendingTransactions();
        }
        fragment.setPermissionListener(listener);
        fragment.requestPermissions(new String[]{permission}, REQUEST_CODE);
    }

    public static class PermissionFragment extends Fragment {

        public PermissionFragment() {
        }

        PermissionListener mPermissionListener;

        public void setPermissionListener(PermissionListener mPermissionListener) {
            this.mPermissionListener = mPermissionListener;
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (grantResults.length != 0) {
                mPermissionListener.onPermissionResult(grantResults[0] == PackageManager.PERMISSION_GRANTED);
            }
        }
    }


    public interface PermissionListener {

        void onPermissionResult(boolean grantedResult);


    }

}
