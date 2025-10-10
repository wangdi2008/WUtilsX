package cn.sylg.wutilsx.shareinterface;
/**
 * 共用成功失败回调
 */
public interface ShareCallBack {
    // 成功
    default void success(){

    }

    // 失败
    default void fail() {
    }
}