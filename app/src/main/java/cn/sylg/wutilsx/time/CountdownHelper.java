package cn.sylg.wutilsx.time;

import android.os.CountDownTimer;

/**
 * @ClassName: CountdownHelper
 * @Description: 倒计时帮助类,增强版倒计时辅助类，支持暂停、继续、重置，链式调用
 * @Author: Wang Di
 * @Date: 2025-09-29 13:28
 * @Version: 1.0
 */
public class CountdownHelper {

    private long millisInFuture;
    private long countDownInterval;
    private long millisRemaining;
    private TimeCountListener timeCountListener;
    private InternalCountDownTimer timer;
    private boolean isRunning = false;

    private CountdownHelper(long millisInFuture, long countDownInterval) {
        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;
        this.millisRemaining = millisInFuture;
        createTimer();
    }

    private void createTimer() {
        timer = new InternalCountDownTimer(millisRemaining, countDownInterval);
    }

    /**
     * 设置倒计时监听器（链式调用）
     */
    public CountdownHelper setListener(TimeCountListener listener) {
        this.timeCountListener = listener;
        return this;
    }

    /**
     * 开始倒计时
     */
    public void start() {
        if (!isRunning) {
            createTimer();
            timer.start();
            isRunning = true;
        }
    }

    /**
     * 暂停倒计时
     */
    public void pause() {
        if (isRunning && timer != null) {
            timer.cancel();
            isRunning = false;
        }
    }

    /**
     * 继续倒计时
     */
    public void resume() {
        if (!isRunning && millisRemaining > 0) {
            createTimer();
            timer.start();
            isRunning = true;
        }
    }

    /**
     * 重置倒计时
     */
    public void reset() {
        pause();
        millisRemaining = millisInFuture;
        createTimer();
    }

    /**
     * 倒计时内部类
     */
    private class InternalCountDownTimer extends CountDownTimer {
        public InternalCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            millisRemaining = millisUntilFinished;
            if (timeCountListener != null) {
                timeCountListener.onTick(millisUntilFinished);
            }
        }

        @Override
        public void onFinish() {
            millisRemaining = 0;
            isRunning = false;
            if (timeCountListener != null) {
                timeCountListener.onFinish();
            }
        }
    }

    /**
     * Builder 创建倒计时实例
     */
    public static CountdownHelper create(long millisInFuture, long countDownInterval) {
        return new CountdownHelper(millisInFuture, countDownInterval);
    }

    /**
     * 倒计时回调接口
     */
    public interface TimeCountListener {
        void onTick(long millisUntilFinished);
        void onFinish();
    }
}