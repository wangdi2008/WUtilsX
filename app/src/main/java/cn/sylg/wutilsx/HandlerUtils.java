package cn.sylg.wutilsx;

import android.os.Handler;

import androidx.annotation.Nullable;

/**
 * @ClassName: HandlerUtils
 * @Description: handler工具类
 * @Author: Wang Di
 * @Date: 2025-09-29 12:40
 * @Version: 1.0
 */
public class HandlerUtils {
    /**
     * 安全清理 Handler 的所有任务和消息
     *
     * @param handler 需要清理的 Handler
     */
    public static void clearHandlerAllTasks(@Nullable Handler handler) {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * 安全移除指定 Runnable
     *
     * @param handler  Handler
     * @param runnable 需要移除的任务
     */
    public static void clearTask(@Nullable Handler handler, @Nullable Runnable runnable) {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}