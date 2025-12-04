package cn.sylg.wutilsx;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: RegexUtils
 * @Description: 正则判断工具类
 * @Author: Wang Di
 * @Date: 2025-12-04 15:09
 * @Version: 1.0
 */
public class RegexUtils {

    /**
     * 正则校验
     */
    private boolean validateRegex(String content, String regex) {
        if (TextUtils.isEmpty(content)) return false;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        return matcher.matches();
    }
}