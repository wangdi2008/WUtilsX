package cn.sylg.wutilsx.time;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: StrUtils
 * @Description: 字符串工具（补充）
 * @Author: Wang Di
 * @Date: 2025-10-10 16:11
 * @Version: 1.0
 */
public class StrUtils {
    /**
     * 将字符串按指定长度切割转换为集合
     *
     * @param str  要转换的字符串
     * @param size 长度
     */
    public static List<String> splitStrToList(String str, int size) {
        List<String> list = new ArrayList<>();
        // 如果字符串长度小于size，则直接添加整个字符串作为唯一片段
        if (str.length() <= size) {
            list.add(str);
        } else {
            // 否则按照size进行分割
            for (int i = 0; i < str.length(); i += size) {
                int end = Math.min(i + size, str.length());
                list.add(str.substring(i, end));
            }
        }
        return list;
    }

    /**
     * 获取真是数字
     * 输入 "12.00" → 输出 "12"
     * 输入 "12.50" → 保留 "12.50"
     * 输入 "13" → 保留 "13"
     * 输入 "abc" → 保留 "abc"
     */
    public static String getRealNumber(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        try {
            double value = Double.parseDouble(input);
            if (value == (int) value) {
                return String.valueOf((int) value);  // 是整数，去掉小数部分
            } else {
                return input;  // 保留原样
            }
        } catch (NumberFormatException e) {
            return input;  // 不是合法数字，直接返回原样
        }
    }
}