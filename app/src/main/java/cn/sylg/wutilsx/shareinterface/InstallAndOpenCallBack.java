package cn.sylg.wutilsx.shareinterface;

public interface InstallAndOpenCallBack {
    default void success() {

    }

    void fail(String msg);
}
