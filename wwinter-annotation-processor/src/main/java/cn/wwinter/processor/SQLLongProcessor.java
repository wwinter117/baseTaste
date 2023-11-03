package cn.wwinter.processor;

import cn.wwinter.annotations.SQLLong;
import cn.wwinter.model.MetaField;

import javax.lang.model.element.Element;

/**
 * ClassName: SQLLongProcessor
 * Package: cn.wwinter.processor
 * Description:
 * Datetime: 2023/11/1
 * Author: zhangdd
 */
public class SQLLongProcessor implements SQLElementProcessor {
    @Override
    public MetaField process(Element field) {
        SQLLong anno = field.getAnnotation(SQLLong.class);
        if (anno == null) {
            return null;
        }
        return generatorMetaField(anno.name(), anno.length(), anno.primaryKey(), anno.allowNull(), anno.unique(), anno.comment());
    }
}
