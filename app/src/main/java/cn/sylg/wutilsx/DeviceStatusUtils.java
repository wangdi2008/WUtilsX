package cn.sylg.wutilsx;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

/**
 * @program: SmartWarehouse
 * @description: 手机状态工具类 音量
 * @author: Wang Di
 * @create: 2024-03-05 9:35
 **/

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class DeviceStatusUtils {

    /**
     * Don't let anyone instantiate this class.
     */
    private DeviceStatusUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 获取系统音量最大值 多少格
     */
    public static int getSystemMaxVoice(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * 获取系统当前音量值 多少格
     */
    public static int getSystemVoice(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * 获取系统当前音量值 换算百分比
     */
    public static int getSystemVoicePercent(Context context) {
        return getSystemVoice(context) * 100 / getSystemMaxVoice(context) ;
    }

    /**
     * 设置系统音量值
     * todo (因为系统不同，max音量不同，如华为15格音量，所以android端和服务端端均按照0~100范围内选值传递，做比例计算,算出等同于几格音量)
     */
    public static void setSystemVoice(Context context, int system_voice) {
        system_voice = (int) Math.ceil(system_voice / 100f * getSystemMaxVoice(context));//按比例计算出应该是该系统的多少格音量
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, system_voice, AudioManager.FLAG_PLAY_SOUND);
//        Log.d(TAG,"修改后系统音量为---"+audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) + "格");
    }
}
