/**
 * Created by wodetianjun on 15/5/28.
 */
package com.ttianjun.mybatis;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据库属性对应列
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface KField {
    String value() default "";
}
