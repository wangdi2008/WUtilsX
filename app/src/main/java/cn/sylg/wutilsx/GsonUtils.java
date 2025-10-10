package cn.sylg.wutilsx;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: GsonUtils
 * @Description: Gson工具类
 * @Author: Wang Di
 * @Date: 2025-09-29 10:21
 * @Version: 1.0
 */
public class GsonUtils {
    private static final String TAG = "GsonUtils";

    private static final Gson gson = new Gson();

    /**
     * 将对象写入 Writer 中
     *
     * @param object 任意对象
     * @param writer 写入目标
     * @return 是否写入成功
     */
    public static boolean writeJson(Object object, Writer writer) {
        if (object == null || writer == null) {
            Log.i(TAG, "对象或 Writer 不能为空");
            return false;
        }
        try {
            gson.toJson(object, writer);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "JSON 写入失败", e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从 Reader 中读取 JSON 并转换为指定类型的对象
     *
     * @param reader JSON 数据来源
     * @param clazz  目标类型
     * @param <T>    泛型类型
     * @return 对象实例，解析失败返回 null
     */
    public static <T> T readJson(BufferedReader reader, Class<T> clazz) {
        try {
            return gson.fromJson(reader, clazz);
        } catch (Exception e) {
            Log.e(TAG, "JSON 解析失败", e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将 JSON 字符串解析为 Map<K, V>
     *
     * @param json       JSON 字符串
     * @param keyClass   Map 的 Key 类型
     * @param valueClass Map 的 Value 类型
     * @param <K>        Key 泛型
     * @param <V>        Value 泛型
     * @return Map<K, V>，解析失败返回空 Map
     */
    public static <K, V> Map<K, V> parseJsonToMap(String json, Class<K> keyClass, Class<V> valueClass) {
        try {
            Type mapType = TypeToken.getParameterized(Map.class, keyClass, valueClass).getType();
            return gson.fromJson(json, mapType);
        } catch (Exception e) {
            System.err.println("JSON 解析为 Map 失败: " + e.getMessage());
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * ✅ 格式化（美化）JSON字符串，使其带缩进和换行。
     * 如果不是标准JSON，将返回原字符串。
     */
    public static String formatJson(String json) {
        if (TextUtils.isEmpty(json)) return "";
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(JsonParser.parseString(json));
        } catch (Exception e) {
            e.printStackTrace();
            return json; // 保底：不是标准JSON则原样返回
        }
    }

}