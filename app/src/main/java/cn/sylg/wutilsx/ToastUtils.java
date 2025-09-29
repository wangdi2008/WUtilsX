package cn.sylg.wutilsx;

/**
 * @ClassName: ToastUtils
 * @Description: 气泡toast工具类
 * @Author: Wang Di
 * @Date: 2025-09-29 13:43
 * @Version: 1.0
 */
import static com.xuexiang.xutil.XUtil.runOnUiThread;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.xuexiang.xutil.XUtil;

public class ToastUtils {
    private static final String TAG = "ToastUtils";
    private static final Context appContext = XUtil.getContext(); // Application Context

    private static Toast toast;

    private ToastUtils() {
        // 工具类禁止实例化
        throw new UnsupportedOperationException("ToastUtils can't be instantiated");
    }

    /**
     * 显示短 Toast
     */
    public static void show(CharSequence text) {
        show(text, Toast.LENGTH_SHORT);
    }

    /**
     * 显示长 Toast
     */
    public static void showLong(CharSequence text) {
        show(text, Toast.LENGTH_LONG);
    }

    /**
     * 资源 id 短 Toast
     */
    public static void show(int resId) {
        show(appContext.getString(resId), Toast.LENGTH_SHORT);
    }

    /**
     * 资源 id 长 Toast
     */
    public static void showLong(int resId) {
        show(appContext.getString(resId), Toast.LENGTH_LONG);
    }

    /**
     * 格式化文本短 Toast
     */
    public static void show(int resId, Object... args) {
        show(String.format(appContext.getString(resId), args), Toast.LENGTH_SHORT);
    }

    /**
     * 格式化文本长 Toast
     */
    public static void showLong(int resId, Object... args) {
        show(String.format(appContext.getString(resId), args), Toast.LENGTH_LONG);
    }

    @SuppressLint("ShowToast")
    public static void show(CharSequence text, int duration) {
        if (appContext == null) {
            Log.w(TAG, "show: appContext 为空");
            return;
        }
        final CharSequence msg = text == null ? "" : text.toString();

        runOnUiThread(() -> {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(appContext, msg, duration);
            toast.show();
        });
    }

    /**
     * 顶部显示 Toast
     */
    @SuppressLint("ShowToast")
    public static void showTop(CharSequence text, int duration) {
        if (appContext == null) {
            Log.w(TAG, "show: appContext 为空");
            return;
        }
        final CharSequence msg = text == null ? "" : text.toString();

        runOnUiThread(() -> {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(appContext, msg, duration);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 150);
            toast.show();
        });
    }

    public static void showTop(CharSequence text) {
        showTop(text, Toast.LENGTH_SHORT);
    }
}
