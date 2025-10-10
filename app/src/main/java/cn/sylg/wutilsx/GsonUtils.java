package cn.sylg.wutilsx;

import static com.xuexiang.xutil.net.JsonUtil.fromJson;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: GsonUtils
 * @Description: Gson工具类
 * @Author: Wang Di
 * @Date: 2025-09-29 10:21
 * @Version: 1.1
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
            Log.e(TAG, "JSON 解析为 Map 失败: " + e.getMessage());
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

    /**
     * 解析Json数组字符串
     *
     * @param json     Json字符串
     * @param classOfT 指定类型对象的Class
     * @param <T>      泛型类型
     * @return List<T>
     */
    public static <T> List<T> fromJsonArray(String json, Class<T> classOfT) {
        try {
            return fromJson(json, TypeToken.getParameterized(List.class, classOfT).getType());
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ------------------------------------------------------------------------
    // 以下为新增 JSON 判断工具方法
    // ------------------------------------------------------------------------

    /**
     * 判断字符串是否为 JSON 数组
     */
    public static boolean isJSONArray(String str) {
        if (TextUtils.isEmpty(str)) return false;
        try {
            JsonElement element = JsonParser.parseString(str);
            return element.isJsonArray();
        } catch (JsonSyntaxException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否不是 JSON 数组
     */
    public static boolean isNotJSONArray(String str) {
        return !isJSONArray(str);
    }

    /**
     * 判断字符串是否为 JSON 对象
     */
    public static boolean isJSONObject(String str) {
        if (TextUtils.isEmpty(str)) return false;
        try {
            JsonElement element = JsonParser.parseString(str);
            return element.isJsonObject();
        } catch (JsonSyntaxException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否不是 JSON 对象
     */
    public static boolean isNotJSONObject(String str) {
        return !isJSONObject(str);
    }

    /**
     * 判断 JSON 数组中所有元素是否都是 JSON 对象
     */
    public static boolean allIsJSONObject(String jsonArrayStr) {
        if (TextUtils.isEmpty(jsonArrayStr)) return false;
        try {
            JsonElement element = JsonParser.parseString(jsonArrayStr);
            if (!element.isJsonArray()) {
                return false;
            }
            JsonArray array = element.getAsJsonArray();
            for (JsonElement item : array) {
                if (!item.isJsonObject()) {
                    return false;
                }
            }
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }
}
