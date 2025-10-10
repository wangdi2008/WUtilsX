package cn.sylg.wutilsx.shareinterface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.sylg.smartwarehouse.common.enums.AuthLevelEnum;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthLevelInterface {
    AuthLevelEnum level();
}
