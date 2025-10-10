package cn.sylg.wutilsx;

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;


/**
 * @description: 对象反射工具类
 * @author: Wang Di
 * @create: 2024/7/2 11:23
 **/
public class ReflectionUtils {
    private static final String TAG = "ReflectionUtils";

    /**
     * 反射调用get方法
     */
    public static Object getPropertyValue(Object obj, String propertyName) {
        Object result = null;
        try {
            Class<?> clazz = obj.getClass();                    // 获取User类的Class对象
            Field field = clazz.getDeclaredField(propertyName); // 获取指定名称的Field对象
            field.setAccessible(true);                          // 设置访问权限（考虑到私有字段）
            result = field.get(obj);                            // 通过Field对象获取属性值
        } catch (Exception e) {
            e.printStackTrace();
            Log.w(TAG, "没有该属性: " + propertyName);
        }
        return result;
    }


    /**
     * 反射调用set方法
     */
    public static void setPropertyValue(Object obj, String propertyName, Object value) {
        Log.i(TAG, "propertyName: " + propertyName + " " + value);
        boolean isEmptyStr = value == null || TextUtils.isEmpty(value.toString());
        try {
            Class<?> clazz = obj.getClass();                    // 获取User类的Class对象
            Field field = clazz.getDeclaredField(propertyName); // 获取指定名称的Field对象
            field.setAccessible(true);                          // 设置访问权限（考虑到私有字段）
            // 获取字段类型
            Class<?> fieldType = field.getType();
            // 类型转换逻辑
            if (fieldType == Long.class || fieldType == long.class) {
                value = isEmptyStr ? 0L : Long.parseLong(value.toString());
            } else if (fieldType == Integer.class || fieldType == int.class) {
                value = isEmptyStr ? 0 : Integer.parseInt(value.toString());
            } else if (fieldType == Double.class || fieldType == double.class) {
                value = isEmptyStr ? 0.0 : Double.parseDouble(value.toString());
            } else if (fieldType == Float.class || fieldType == float.class) {
                value = isEmptyStr ? 0.0f : Float.parseFloat(value.toString());
            } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                value = !isEmptyStr && Boolean.parseBoolean(value.toString());
            } else if (fieldType == String.class) {
                value = isEmptyStr ? "" : value.toString();
            }
            field.set(obj, value); // 通过Field对象设置属性值
        } catch (Exception e) {
            e.printStackTrace();
            Log.w(TAG, "无法设置属性: " + propertyName);
        }
    }
}
