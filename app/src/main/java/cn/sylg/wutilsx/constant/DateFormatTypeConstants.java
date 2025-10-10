package cn.sylg.wutilsx.constant;

import android.annotation.SuppressLint;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @ClassName: DateFormatTypeConstants
 * @Description: 转换类型（补充）
 * @Author: Wang Di
 * @Date: 2025-10-10 16:07
 * @Version: 1.0
 */
public class DateFormatTypeConstants {
    public static final ThreadLocal<DateFormat> yyyyMMNoSep = new ThreadLocal<DateFormat>() {
        @SuppressLint("SimpleDateFormat")
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DateFormatConstants.yyyyMMNoSep);
        }
    };
}