package cn.sylg.wutilsx.location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

/**
 * @ClassName: LocationHelper
 * @Description: 定位帮助类
 * @Author: Wang Di
 * @Date: 2025-09-29 12:46
 * @Version: 1.0
 */
public class LocationHelper {
    private static final String TAG = "LocationHelper";

    /**
     * 判断是否开启位置服务
     */
    public static boolean locationServiceEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;
    }

    @SuppressLint("MissingPermission")
    public static void getLastKnownLocation(Activity activity, OnLocationCallback callback) {
        if (!locationServiceEnable(activity)) {
            Log.i(TAG, "未开启定位");
        }
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        Location location = null;
        try {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "getLastLocation: 获取定位失败" + e.getMessage());
        }
        if (callback != null) {
            callback.onLocationResult(location);
        }
    }
}