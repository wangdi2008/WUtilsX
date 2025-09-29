package cn.sylg.wutilsx;

import com.xuexiang.xutil.common.StringUtils;

import java.util.regex.Pattern;

/**
 * @ClassName: CheckUtils
 * @Description: 各种常用校验
 * @Author: Wang Di
 * @Date: 2025-09-29 8:48
 * @Version: 1.0
 */
public class CheckUtils {
    // MAC 地址的正则表达式：支持 00:11:22:33:44:55、00-11-22-33-44-55、0011.2233.4455
    public static final String MAC_ADDRESS_RE = "^(?:([0-9A-Fa-f]{2}[:-]){5}[0-9A-Fa-f]{2}|([0-9A-Fa-f]{4}\\.[0-9A-Fa-f]{4}\\.[0-9A-Fa-f]{4}))$";


    private static final Pattern MAC_PATTERN = Pattern.compile(MAC_ADDRESS_RE);

    /**
     * 校验字符串是否是MAC地址
     *
     * @param macStr 待校验的字符串
     * @return 是否为合法MAC地址
     */
    public static boolean isMac(String macStr) {
        return macStr != null && MAC_PATTERN.matcher(macStr).matches();
    }

    /**
     * 判断字符串是否为正整数
     */
    public static boolean isPositiveInteger(String str) {
        if (StringUtils.isEmpty(str)) return false;
        return str.matches("\\d+");
    }

    /**
     * 判断字符串是否为负整数
     */
    public static boolean isNegativeInteger(String str) {
        if (StringUtils.isEmpty(str)) return false;
        return str.matches("-\\d+");
    }

    /**
     * 判断是否是纯字母
     */
    public static boolean isAlpha(String str) {
        if (StringUtils.isEmpty(str)) return false;
        return str.matches("[a-zA-Z]+");
    }

    /**
     * 判断是否是字母或数字
     */
    public static boolean isAlphanumeric(String str) {
        if (StringUtils.isEmpty(str)) return false;
        return str.matches("[a-zA-Z0-9]+");
    }

    /**
     * 判断是否是手机号（中国大陆）
     */
    public static boolean isMobile(String str) {
        if (StringUtils.isEmpty(str)) return false;
        return str.matches("^1[3-9]\\d{9}$");
    }

    /**
     * 判断是否是邮箱
     */
    public static boolean isEmail(String str) {
        if (StringUtils.isEmpty(str)) return false;
        return str.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    /**
     * 判断是否为数字（整数或小数）
     *
     * @param strNum 待校验字符串
     * @return true：数字   false：不是数字
     */
    public static boolean isDecimal(String strNum) {
        if (strNum == null || strNum.trim().isEmpty()) {
            return false;
        }
        return strNum.matches("^[-+]?\\d+(\\.\\d+)?$");
    }

}