package cn.sylg.wutilsx;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * @ClassName: DecimalFormatUtils
 * @Description: 数据转换工具类
 * @Author: Wang Di
 * @Date: 2025-09-29 9:51
 * @Version: 1.0
 */
public class DecimalFormatUtils {

    public static DecimalFormat getDecimalFormat(String format) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
        symbols.setDecimalSeparator('.');
        return new DecimalFormat(format, symbols);
    }
}