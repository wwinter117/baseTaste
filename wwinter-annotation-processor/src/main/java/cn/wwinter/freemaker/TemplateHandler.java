package cn.wwinter.freemaker;

import cn.wwinter.model.TableInfo;

/**
 * ClassName: TemplateHandler
 * Package: cn.wwinter.freemaker
 * Description:
 * Datetime: 2023/11/9
 * Author: zhangdd
 */
public interface TemplateHandler<T, R> {
    R handle(T t);
}
