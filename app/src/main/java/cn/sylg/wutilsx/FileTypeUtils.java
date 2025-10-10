package cn.sylg.wutilsx;

import com.xuexiang.xutil.file.FileUtils;

import cn.sylg.wutilsx.constant.FileTypeConstants;

/**
 * @ClassName: FileTypeUtils
 * @Description: 文件类型工具类
 * @Author: Wang Di
 * @Date: 2025-10-10 14:46
 * @Version: 1.0
 */
public class FileTypeUtils {
    private static final String TAG = "FileTypeUtils";

    public static boolean isImage(String path) {
        String ext = FileUtils.getFileExtension(path);
        return ext.equalsIgnoreCase(FileTypeConstants.TYPE_JPG)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_JPEG)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_PNG)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_GIF)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_BMP)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_WEBP)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_HEIC);
    }

    public static boolean isVideo(String path) {
        String ext = FileUtils.getFileExtension(path);
        return ext.equalsIgnoreCase(FileTypeConstants.TYPE_MP4)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_AVI)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_MKV)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_MOV)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_FLV)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_WMV);
    }

    public static boolean isAudio(String path) {
        String ext = FileUtils.getFileExtension(path);
        return ext.equalsIgnoreCase(FileTypeConstants.TYPE_MP3)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_WAV)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_AAC)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_FLAC)
                || ext.equalsIgnoreCase(FileTypeConstants.TYPE_OGG);
    }
}