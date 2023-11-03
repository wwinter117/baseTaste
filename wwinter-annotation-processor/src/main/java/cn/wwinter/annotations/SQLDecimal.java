package cn.wwinter.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: SQLString
 * Package: cn.wwinter
 * Description:
 * Datetime: 2023/10/26
 * Author: zhangdd
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface SQLDecimal {
    String name() default "";

    int length() default 0;

    boolean primaryKey() default false;

    boolean allowNull() default true;

    boolean unique() default false;
    
    String comment() default "";
}
