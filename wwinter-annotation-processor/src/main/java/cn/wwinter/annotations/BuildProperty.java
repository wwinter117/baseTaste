package cn.wwinter.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: BuildProperty
 * Package: cn.wwinter
 * Description:
 * Datetime: 2023/10/30
 * Author: zhangdd
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface BuildProperty {

}
