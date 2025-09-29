package cn.sylg.wutilsx;

/**
 * @ClassName: CompareUtils
 * @Description: 比较器工具类
 * @Author: Wang Di
 * @Date: 2025-09-29 9:24
 * @Version: 1.0
 */
public class CompareUtils {
    /**
     * 比较字符串
     * 1、按照数字小在前大在后
     * 2、a 是数字，b 不是数字，数字排前面
     * 3、b 是数字，a 不是数字，数字排前面
     * 4、都不是数字，按字典序排序（默认）
     */
    public static int compareNum(String a, String b) {
        boolean isANumber = a.matches("\\d+");
        boolean isBNumber = b.matches("\\d+");
        if (isANumber && isBNumber) {
            // 两个 key 都是数字，按数值大小排序
            return Integer.compare(Integer.parseInt(a), Integer.parseInt(b));
        } else if (isANumber) {
            // a 是数字，b 不是数字，数字排前面
            return -1;
        } else if (isBNumber) {
            // b 是数字，a 不是数字，数字排前面
            return 1;
        } else {
            // 都不是数字，按字典序排序（默认）
            return a.compareToIgnoreCase(b);
        }
    }
}