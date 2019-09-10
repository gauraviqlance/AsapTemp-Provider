package com.iqlance.asaptemp.provider.utils;

import android.content.Context;
import android.content.pm.PackageManager;

// https://stackoverflow.com/questions/7203668/how-permission-can-be-checked-at-runtime-without-throwing-securityexception
public class PermissionChecker {
    public static boolean checkAccessFineLocationPermission(Context context, String permission) {
        int res = context.checkCallingOrSelfPermission(permission);

        return (res == PackageManager.PERMISSION_GRANTED);
    }

    public static boolean checkCameraPermission(Context context, String permission) {
        int res = context.checkCallingOrSelfPermission(permission);

        return (res == PackageManager.PERMISSION_GRANTED);
    }

    public static boolean checkStoragePermission(Context context, String permission) {
        int res = context.checkCallingOrSelfPermission(permission);

        return (res == PackageManager.PERMISSION_GRANTED);
    }


}
