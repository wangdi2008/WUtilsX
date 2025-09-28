package cn.sylg.wutilsx;

import android.content.res.AssetManager;

import com.xuexiang.xutil.XUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class AssetsUtils {
    private static final String TAG = "AssetsUtils";
    /**
     * 读取assets文件
     * @param path 文件路径    格式为 assets下的路径 如： host_setting/host_type.txt
     * @return 解析后的字符串
     */
    public static String readFile(String path) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = XUtil.getAssetManager();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(path)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 读取assets文件 每行增加\r\n
     * @param path 文件路径    格式为 assets下的路径 如： host_setting/host_type.txt
     * @return 解析后的字符串
     */
    public static String readFileWithRN(String path) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = XUtil.getAssetManager();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(path)));
            String line;
            while ((line = bf.readLine()) != null) {
                if (!stringBuilder.toString().equals("")) {
                    stringBuilder.append("\r\n");
                }
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 按行读文件
     * @param fileName 文件路径
    */
    public static List<String> readFileToList(String fileName) {
        //将json数据变成字符串集合
        List<String> fileContent = new ArrayList<String>();
        //获取assets资源管理器
        AssetManager assetManager = XUtil.getAssetManager();
        try {
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                fileContent.add(line);
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    /**
     * 读取assets文件 并替换指定字符串
     * @param fileName 文件名
     * @return 解析后的字符串
     */
    public static String readFileAndReplace(String fileName,String pattern) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = XUtil.getAssetManager();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                line=line.replaceAll(pattern, "\n");
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
