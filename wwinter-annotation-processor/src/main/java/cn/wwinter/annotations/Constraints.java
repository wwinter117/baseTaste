package cn.wwinter.annotations;

/**
 * ClassName: Constraints
 * Package: cn.wwinter.annotations
 * Description:
 * Datetime: 2023/11/1
 * Author: zhangdd
 */
public @interface Constraints {
    boolean primaryKey() default false;

    boolean allowNull() default true;

    boolean unique() default false;
}
