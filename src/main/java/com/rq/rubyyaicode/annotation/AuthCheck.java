package com.rq.rubyyaicode.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
    /**
     * @description 必须有某个角色
     * @return: 角色名称
     * @author RQ
     * @date: 2025/10/25 下午6:40
     */
    String mustRole() default "";
}
