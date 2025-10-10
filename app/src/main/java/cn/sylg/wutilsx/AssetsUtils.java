package cn.sylg.wutilsx;


import android.content.res.AssetManager;
import android.util.Log;

import com.xuexiang.xutil.XUtil;
import com.xuexiang.xutil.file.FileIOUtils;
import com.xuexiang.xutil.file.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AssetsUtils {
    private static final String TAG = "AssetsUtils";

    /**
     * 获取AssetManager
     *
     * @return AssetManager
     */
    private static AssetManager getAssetManager() {
        return XUtil.getAssetManager();
    }

    /**
     * 通用读取方法
     *
     * @param path    文件路径
     * @param withRN  是否在每行末尾追加\r\n
     * @param pattern 替换模式字符串（可为空）
     * @return 文件内容
     */
    private static String readFileInternal(String path, boolean withRN, String pattern) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(getAssetManager().open(path)))) {
            String line;
            while ((line = bf.readLine()) != null) {
                if (pattern != null) {
                    // 如果要正则替换，可以改成 replaceAll
                    line = line.replace(pattern, "\n");
                }
                if (withRN && stringBuilder.length() > 0) {
                    stringBuilder.append("\r\n");
                }
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "readFileInternal error: " + path, e);
        }
        return stringBuilder.toString();
    }

    /**
     * 读取assets文件
     *
     * @param path 文件路径    格式为 assets下的路径 如： host_setting/host_type.txt
     * @return 解析后的字符串
     */
    public static String readFile(String path) {
        return readFileInternal(path, false, null);
    }

    /**
     * 读取assets文件 每行增加\r\n
     *
     * @param path 文件路径    格式为 assets下的路径 如： host_setting/host_type.txt
     * @return 解析后的字符串
     */
    public static String readFileWithRN(String path) {
        return readFileInternal(path, true, null);
    }

    /**
     * 按行读文件
     *
     * @param fileName 文件路径
     * @return 文件内容集合
     */
    public static List<String> readFileToList(String fileName) {
        List<String> fileContent = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(getAssetManager().open(fileName)))) {
            String line;
            while ((line = bf.readLine()) != null) {
                fileContent.add(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "readFileToList error: " + fileName, e);
        }
        return fileContent;
    }

    /**
     * 读取assets文件 并替换指定字符串
     *
     * @param fileName 文件名
     * @param pattern  要替换的字符串
     * @return 解析后的字符串
     */
    public static String readFileAndReplace(String fileName, String pattern) {
        return readFileInternal(fileName, false, pattern);
    }


    /**
     * 从asset复制文件到内存
     */
    public static void copyFromAssets(String assetDirName, String outDir) {
        Log.i(TAG, "从asset复制文件到内存: " + assetDirName + " " + outDir);
        String[] files;
        try {
            files = getAssetManager().list(assetDirName);
            FileUtils.createOrExistsDir(outDir);
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                String fileName = files[i];
                Log.i(TAG, "子文件: " + fileName);
                //判断是否为文件夹
                if (!fileName.contains(".")) {
                    if (0 == assetDirName.length()) {
                        copyFromAssets(fileName, outDir + fileName + "/");
                    } else {
                        copyFromAssets(assetDirName + "/" + fileName, outDir + fileName + "/");
                    }
                    continue;
                }
                InputStream in;
                if (0 != assetDirName.length()) {
                    in = getAssetManager().open(assetDirName + "/" + fileName);
                } else {
                    in = getAssetManager().open(fileName);
                }
                File outFile = new File(outDir, fileName);
                FileIOUtils.writeFileFromIS(outFile.getPath(), in);
                Log.w(TAG, "copyFromAssets: 文件复制成功 " + assetDirName + " " + outDir);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.w(TAG, "copyFromAssets: 文件复制失败 " + assetDirName + " " + outDir);
        }
    }
}

