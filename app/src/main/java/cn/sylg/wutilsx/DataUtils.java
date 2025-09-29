package cn.sylg.wutilsx;

import static com.xuexiang.xutil.common.StringUtils.isInteger;
import static cn.sylg.wutilsx.CheckUtils.isDecimal;
import static cn.sylg.wutilsx.DecimalFormatUtils.getDecimalFormat;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @ClassName: DataUtils
 * @Description: 数据工具类
 * @Author: Wang Di
 * @Date: 2025-09-29 9:44
 * @Version: 1.0
 */
public class DataUtils {
    /**
     * 将long型数据转换为字节数组
     */
    public static byte[] longToByte(long number) {
        long temp = number;
        byte[] b = new byte[8];
        for (int i = 0; i < b.length; i++) {
            b[i] = Long.valueOf(temp & 0xff).byteValue();
            // 将最低位保存在最低位
            temp = temp >> 8; // 向右移8位
        }
        return b;
    }

    /**
     * 将long型数据转换为字节数组
     */
    public static byte[] longToByte4(long number) {
        long temp = number;
        byte[] b = new byte[4];
        for (int i = 0; i < b.length; i++) {
            b[i] = Long.valueOf(temp & 0xff).byteValue();
            // 将最低位保存在最低位
            temp = temp >> 8; // 向右移8位
        }
        return b;
    }

    /**
     * 将short型数据转换为字节数组
     */
    public static byte[] shortToByte(short number) {
        short temp = number;
        byte[] b = new byte[2];
        for (int i = 0; i < b.length; i++) {
            b[i] = Short.valueOf((short) (temp & 0xff)).byteValue();
            temp = (short) (temp >> 8);
        }
        return b;
    }

    /**
     * 将指定字节数组转换为字符串，默认使用UTF-8编码
     */
    public static String getString(byte[] data) {
        return new String(data, StandardCharsets.UTF_8);
    }

    /**
     * 将指定字节数组转换为long型 默认字节序为小端字节序
     */
    public static long getLong(byte[] buf) {
        try {
            return getLong(buf, true);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将指定字节数组转换为long型 默认字节序为小端字节序
     * todo 为了记录导致问题的参数code
     */
    public static long getLong(byte[] buf, String code) {
        try {
            return getLong(buf, true);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将指定字节数组转换为long型
     *
     * @param buf 待转换字节数组
     * @param asc true 代表使用小端字节序，false 代表使用大端字节序
     * @return 转换结果
     */
    public static long getLong(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 8) {
            throw new IllegalArgumentException("byte array size > 8 !");
        }
        long r = 0;
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x00000000000000ff);
            }
        else
            for (int i = 0; i < buf.length; i++) {
                r <<= 8;
                r |= (buf[i] & 0x00000000000000ff);
            }
        return r;
    }

    /**
     * 将指定字节数组转换为int型 默认字节序为小端字节序
     */
    public static int getInt(byte[] buf) {
        try {
            return getInt(buf, true);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将指定字节数组转换为int型
     *
     * @param buf 待转换字节数组
     * @param asc true 代表使用小端字节序，false 代表使用大端字节序
     * @return 转换结果
     */
    public static int getInt(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 4) {
            throw new IllegalArgumentException("byte array size > 4 !");
        }
        int r = 0;
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        else
            for (int i = 0; i < buf.length; i++) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        return r;
    }

    /**
     * 将指定字节数组转换为short型 默认字节序为小端字节序
     */
    public static short getShort(byte[] buf) {
        return getShort(buf, true);
    }

    /**
     * 将指定字节数组转换为short型 默认字节序为小端字节序
     *
     * @param buf 待转换字节数组
     * @param asc true 代表使用小端字节序，false 代表使用大端字节序
     * @return 转换结果
     */
    public static short getShort(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 2) {
            throw new IllegalArgumentException("byte array size > 2 !");
        }
        short r = 0;
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        else
            for (int i = 0; i < buf.length; i++) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        return r;
    }

    /**
     * 将指定字节数组转换为byte型 默认字节序为小端字节序
     *
     * @param buf 待转换字节数组
     * @return 转换结果
     */
    public static short getByte(byte[] buf) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 1) {
            throw new IllegalArgumentException("byte array size > 2 !");
        }
        return buf[0];
    }

    /**
     * 十六进制字符串转换为字节数组
     */
    public static byte[] convertHEXString2bytes(String data) {
        data = data.toUpperCase();
        char[] messageChars = data.toCharArray();
        String str = "0123456789ABCDEF";
        int n = 0;
        byte[] bytes = new byte[data.length() / 2];
        for (int i = 0; i < data.length() / 2; i++) {
            n = str.indexOf(messageChars[2 * i]) * 16;
            n += str.indexOf(messageChars[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return bytes;
    }

    /**
     * 字节数组转换为十六进制字符串
     */
    public static String convertBytes2HEXString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) return "";
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(hexArray[(b >> 4) & 0xF]); // 取高4位
            sb.append(hexArray[b & 0xF]);        // 取低4位
        }
        return sb.toString();
    }

    /**
     * 字节数组转换为十六进制字符串
     */
    public static String convertBytes2HEXString(Byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append("" + "0123456789ABCDEF".charAt(0xf & bytes[i] >> 4) + "0123456789ABCDEF".charAt(bytes[i] & 0xf));
        }
        return sb.toString();
    }

    /**
     * 取得字节中的某一位
     *
     * @param data 字节
     * @param num  每个字节由8位组成，按照从右到左进行编号，分别从位0到7位。
     * @return
     */
    public static boolean getBooleanFrom(byte data, int num) {
        byte mask = (byte) (1 << num);
        return 0 != (data & mask);
    }

    /**
     * 取得字节数组中的某一位
     *
     * @param data 字节数组
     * @param num  将数组按照从低字节、低位到高字节、高位依次编号。例如4字节编号从0到31。
     * @return
     */
    public static boolean getBooleanFrom(byte[] data, int num) {
        try {
            return getBooleanFrom(data[num / 8], num % 8);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 取得一个数(展开)上的某一位
     *
     * @param num      要取的数
     * @param position 要取第几位
     * @return 0或1
     */
    public static int getIntFromNum(long num, int position) {
        long mask = 1 << position;
        if (0 != (num & mask)) {
            return 1;
        }
        return 0;
    }

    /**
     * 取得一个数(展开)上的某一位
     *
     * @param num      要取的数
     * @param position 要取第几位
     * @return 1--true 0--false
     */
    public static boolean getBooleanFromNum(long num, int position) {
        long mask = 1 << position;
        if (0 != (num & mask)) {
            return true;
        }
        return false;
    }

    /**
     * BCD码转换成十进制
     */
    public static int bcd2dec(int bcd) {
        return (bcd & 15) + ((bcd >> 4) * 10);
    }

    /**
     * 将long型时间变量按照年-月-日 小时(24):分:秒格式转换为字符串
     */
    public static String formatTime(long time) {
        return formatTime(time, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将long型时间变量按指定格式转换为字符串
     *
     * @param time   long型时间
     * @param format 格式字符串
     * @return 转换结果
     */
    public static String formatTime(long time, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar timeC = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        timeC.setTimeInMillis(time);
        return df.format(timeC.getTime());
    }

    /**
     * 将int型的数据按位转换为字节数组
     * 按照从左到右的顺序  如 130  16进制为82  默认下4字节是 0x00 0x00 0x00 0x82  经过转换为后  82 00 00 00
     *
     * @param intData  int值
     * @param bytesLen 字节数
     * @return 字节数组
     */
    public static byte[] converInt2Btyes(int intData, int bytesLen) {
        String hexString = Integer.toHexString(intData);
        // 如果位数不够，说明高位为0,将不足的位补上。
        for (int i = hexString.length(); i < bytesLen * 2; i++) {
            hexString = "0" + hexString;
        }
        char[] charArray = hexString.toCharArray();
        StringBuffer sb = new StringBuffer();

        // 将字符按位重新组合
        for (int i = bytesLen - 1; i >= 0; i--) {
            sb.append(charArray[i * 2]);
            sb.append(charArray[i * 2 + 1]);
        }
        return convertHEXString2bytes(sb.toString());
    }

    public static byte[] makeCommand(String cmdAddress, int length) {
        char[] charArray = cmdAddress.toCharArray();
        StringBuffer sb = new StringBuffer();
        sb.append(charArray[2]);
        sb.append(charArray[3]);
        sb.append(charArray[0]);
        sb.append(charArray[1]);
        byte[] data = convertHEXString2bytes(sb.toString());
        byte[] data1 = new byte[3];
        System.arraycopy(data, 0, data1, 0, 2);
        data1[2] = (byte) length;
        return data1;
    }

    /**
     * 布尔数组转换成字节数组
     */
    public static byte[] getBytesFrom(boolean[] data) {
        if (data.length % 8 != 0) {
            throw new IllegalArgumentException("boolean array length is not multiples of 8!");
        }
        byte[] bytes = new byte[data.length / 8];
        for (int i = 0; i < data.length; i++) {
            if (data[i]) {
                bytes[i / 8] += 1 << i % 8;
            }
        }
        return bytes;
    }

    /**
     * 将浮点型变量按照0.00的格式转换为字符串 例如 1.6f 转换结果为1.60
     */
    public static String tranFloatToString(float f) {
        DecimalFormat df = getDecimalFormat("0.00");
        return df.format(f);
    }

    /**
     * 将浮点型变量按照指定的格式转换为字符串
     */
    public static String tranFloatToString(float f, String format) {
        DecimalFormat df = getDecimalFormat(format);
        return df.format(f);
    }

    /**
     * 将long型变量按照指定的格式转换为字符串
     */
    public static String tranLongToString(long f, String format) {
        DecimalFormat df = getDecimalFormat(format);
        return df.format(f);
    }

    /**
     * 将一个(long型)数按照 小数位数 变为一个字符串形式的小数
     *
     * @param num        要转换的数据
     * @param decimalLen 小数位数
     * @return 字符串形式
     */
    public static String accuracyData(Long num, int decimalLen) {
        String data;
        if (decimalLen > 0) {
            StringBuilder format = new StringBuilder("0.");
            for (int i = 0; i < decimalLen; i++) {
                format.append("0");
            }
            DecimalFormat df = getDecimalFormat(format.toString());
            data = df.format(num / Math.pow(10, decimalLen));
        } else {
            data = String.valueOf(num);
        }
        return data;
    }

    /**
     * 十进制转十六进制
     *
     * @param dec data
     */
    public static int dec2Hex(int dec) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int tmp = (dec >> (7 - i % 8) * 4) & 0x0f;
            if (tmp < 10)
                sb.append(tmp);
            else
                sb.append((char) ('A' + (tmp - 10)));
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * 十六进制转十进制
     *
     * @param dec data
     */
    public static int hex2Dec(int dec) {
        return Integer.valueOf(String.valueOf(dec), 16);
    }


    /**
     * 格式化整数，格式为：+090，-011
     *
     * @param strDigit 需要格式化的整数
     * @param num      位数
     * @return 格式化后的整数
     */
    public static String formatIntegerDigits(String strDigit, int num) {
        String formatedDigit = "";
        // 如果是整数的场合
        if (isInteger(strDigit)) {
            // 待格式数据
            int digit = Integer.valueOf(strDigit);
            // 得到一个NumberFormat的实例
            NumberFormat nf = NumberFormat.getInstance();
            // 设置是否使用分组
            nf.setGroupingUsed(false);
            // 设置最大整数位数
            nf.setMaximumIntegerDigits(num);
            // 设置最小整数位数
            nf.setMinimumIntegerDigits(num);

            formatedDigit = nf.format(digit);

            if (!formatedDigit.startsWith("-")) {
                formatedDigit = "+" + formatedDigit;
            }
        }

        // 返回格式化的整数
        return formatedDigit;
    }

    /**
     * 格式化数字，根据整数位和小数位查缺补零
     *
     * @param strNum   需要格式化的数字
     * @param intDegit 整数位数
     * @param decDegit 小数位
     * @return 格式化后的数字
     */
    public static String formatDecimal(String strNum, int intDegit, int decDegit) {
        String formatedDigit = "";
        // 如果是数字的场合
        if (isDecimal(strNum)) {
            // 待格式数据
            float digit = Float.valueOf(strNum);
            // 得到一个NumberFormat的实例
            NumberFormat nf = NumberFormat.getNumberInstance();
            // 设置是否使用分组
            nf.setGroupingUsed(false);
            // 小数点前保留几位
            nf.setMinimumIntegerDigits(intDegit);
            // 小数点后保留几位
            nf.setMinimumFractionDigits(decDegit);

            formatedDigit = nf.format(digit);

            if (!formatedDigit.startsWith("-")) {
                formatedDigit = "+" + formatedDigit;
            }

        }

        // 返回格式化的整数
        return formatedDigit;
    }

    /**
     * 将无符号 long 转换为带符号数（适用于 16 位无符号数）
     *
     * @param num 待转换的 long 数值（0 ~ 65535）
     * @return 转换后的带符号 long，范围：-32768 ~ 32767
     */
    public static long negativeTrans(long num) {
        if (num >= 32768) {
            return num - 65535;
        } else {
            return num;
        }
    }

    /**
     * 将无符号 int 转换为带符号数（适用于 16 位无符号数）
     *
     * @param num 待转换的 int 数值（0 ~ 65535）
     * @return 转换后的带符号 int，范围：-32768 ~ 32767
     */
    public static int negativeTrans(int num) {
        if (num >= 32768) {
            return num - 65536;
        } else {
            return num;
        }
    }


    /**
     * 把byte转为字符串的bit
     */
    public static String byteToBit(byte b) {
        return "" + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1) + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1) + (byte) ((b >> 3) & 0x1)
                + (byte) ((b >> 2) & 0x1) + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }
    /**
     * 把字符串的bit转为byte
     */
    public static byte bit2byte(String bString) {
        byte result = 0;
        for (int i = bString.length() - 1, j = 0; i >= 0; i--, j++) {
            result += (Byte.parseByte(bString.charAt(i) + "") * Math.pow(2, j));
        }
        return result;
    }

    /**
     * 范围内获取随机数
     */
    public static int getRandom(int max, int min) {
        return (int) (Math.random() * (max - min) + min);
    }

    /**
     * 左补零处理
     *
     * @param para 需要左补零 的数字
     * @return 左补零后的字符串
     */
    public static String leftAddZero(int para) {
        String pattern = "00";
        DecimalFormat format = getDecimalFormat(pattern);
        return format.format(para);
    }

    /**
     * 小数转为整数(通过乘以10的decimal次方)    如 1.8  传入 decimalLen = 1 则为18  传入 decimalLen = 2则为180
     * 注意：要保证decimal的值不能小于value的小数位 否则会出现误差
     *
     * @param value      需要转换的值
     * @param decimalLen 要移动的位数
     * @return 转换后的整数
     */
    public static Long decimalToLong(float value, int decimalLen) {
        return (long) (float) (value * Math.pow(10, decimalLen));
    }

    /**
     * 将一个数据低八位展开后,对其某位进行改值
     * 如 isOne=false value=15 index=2 代表第二位变为0 即00001111 转换为00001101
     *
     * @param isOne 是否是变成1
     * @param value 要改变的原始值
     * @param index 改变的位数
     */
    public static int changeByte(boolean isOne, byte value, int index) {
        int n = 0;
        if (isOne) {
            n = (int) Math.pow(2, index);
            return value | n;
        } else {
            for (int i = 0; i < 8; i++) {
                if (i != index) {
                    n += (int) Math.pow(2, i);
                }
            }
            return value & n;
        }
    }

    /**
     * 将一个数据低32位展开后,对其某位进行改值
     * 如 isOne=false value=15 index=2 代表第二位变为0 即00001111 转换为00001101
     *
     * @param isOne 是否是变成1
     * @param value 要改变的原始值
     * @param index 改变的位数
     */
    public static long changeLong(boolean isOne, long value, int index) {
        int n = 0;
        if (isOne) {
            n = (int) Math.pow(2, index);
            return value | n;
        } else {
            for (int i = 0; i < 64; i++) {
                if (i != index) {
                    n += (long) Math.pow(2, i);
                }
            }
            return value & n;
        }
    }

    /**
     * 获取数字的小数位数
     * 如：0.001--3、0.01--2、0.1-1、1.0--0、1.1--1
     *
     * @param numStr
     */
    public static int getNumberDecimalLen(String numStr) {
        BigDecimal bigDecimal = new BigDecimal(numStr);
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }

    /**
     * 移除没有用的 0
     * 如：100.0 -- 100
     *
     * @param numStr 要转换的字符串
     */
    public static String removeUnsableZero(String numStr) {
        return new BigDecimal(numStr).stripTrailingZeros().toPlainString();
    }

    /**
     * 合并两个数组
     *
     * @param bytes1 数组1
     * @param bytes2 数组2
     */
    public static byte[] concatArray(byte[] bytes1, byte[] bytes2) {
        byte[] result = Arrays.copyOf(bytes1, bytes1.length + bytes2.length);
        System.arraycopy(bytes2, 0, result, bytes1.length, bytes2.length);
        return result;
    }

    /**
     * 转换带符号数据。
     *
     * @param data   字节数组，从主板取得的数据
     * @param length 数据长度
     */
    public static long translateSignParameter(byte[] data, int length) {
        long dataValue = 0;
        if (length == 1) {
            dataValue = getByte(data);        // 单字节数据
        } else if (length == 2) {
            dataValue = getShort(data, true); // 双字节用short转换，防止符号丢失
        } else if (length == 4) {
            dataValue = getInt(data, true);   // 四字节用int转换，防止符号丢失
        }
        return dataValue;
    }
}