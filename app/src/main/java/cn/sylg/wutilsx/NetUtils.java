package cn.sylg.wutilsx;

import static com.xuexiang.xutil.net.NetworkUtils.isHaveInternet;

import com.github.wangdi2008.wutilsx.R;

/**
 * @ClassName: NetUtils
 * @Description: 网络工具（补充）
 * @Author: Wang Di
 * @Date: 2025-10-10 16:13
 * @Version: 1.0
 */
public class NetUtils {


    /**
     * 检查网络是否可用 todo 带Toast提示
     *
     * @return true：可用；false：不可用
     */
    public static boolean isHaveInternetToast() {
        if (!isHaveInternet()) {
            ToastUtils.show(R.string.common_check_net);
            return false;
        } else {
            return true;
        }
    }
}