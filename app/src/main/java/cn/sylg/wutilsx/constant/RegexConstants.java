package cn.sylg.wutilsx.constant;

/**
 * @ClassName: RegexConstants
 * @Description: 正则常量
 * @Author: Wang Di
 * @Date: 2025-12-04 15:12
 * @Version: 1.0
 */
public class RegexConstants {
    /**
     * 密码正则表达式：
     * 至少包含一个数字 (0-9)
     * 至少包含一个小写字母 (a-z)
     * 至少包含一个大写字母 (A-Z)
     * 至少包含一个特殊字符 (!@#$%^&*)
     * 密码长度至少 大于等于6  小于等于15 位
     */
    public static final String PWD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,15}";
}