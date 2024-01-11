package top.codx.todotask.annotation;


import top.codx.todotask.common.enums.BusinessType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口调用日志注解
 *
 * @author Liuch
 * @since 2023-05-20 17:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    /**
     * 模块
     */
    String title();

    BusinessType businessType();
}
