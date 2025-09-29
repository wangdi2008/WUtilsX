package cn.sylg.wutilsx;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;

/**
 * @ClassName: ShakeUtils
 * @Description: 震动提醒工具类
 * @Author: Wang Di
 * @Date: 2025-09-29 12:57
 * @Version: 1.0
 */
public class ShakeUtils {
    public static final long VIBRATION_INSTANT = 100;
    public static final long VIBRATION_SHORT = 300;
    public static final long VIBRATION_LONG = 500;

    /**
     * 震动提醒--瞬间
     */
    public static void vibrationInstant(Context context) {
        vibrate(context, VIBRATION_INSTANT);
    }

    /**
     * 震动提醒
     */
    public static void vibrationShort(Context context) {
        vibrate(context, VIBRATION_SHORT);
    }

    /**
     * 震动提醒
     */
    public static void vibrationLong(Context context) {
        vibrate(context, VIBRATION_LONG);
    }

    /**
     * 震动提醒
     *
     * @param context 上下文
     * @param time    震动时长，单位毫秒
     */
    public static void vibrate(Context context, long time) {
        if (context == null) return;

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator == null || !vibrator.hasVibrator()) return;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(time, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(time);
        }
    }
}