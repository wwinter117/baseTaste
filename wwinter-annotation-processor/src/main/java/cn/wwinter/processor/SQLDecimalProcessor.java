package cn.wwinter.processor;

import cn.wwinter.annotations.SQLDecimal;
import cn.wwinter.annotations.SQLInteger;
import cn.wwinter.model.MetaField;

import javax.lang.model.element.Element;

/**
 * ClassName: SQLStringProcessor
 * Package: cn.wwinter.processor
 * Description:
 * Datetime: 2023/11/1
 * Author: zhangdd
 */
public class SQLDecimalProcessor implements SQLElementProcessor {

    @Override
    public MetaField process(Element field) {
        SQLDecimal anno = field.getAnnotation(SQLDecimal.class);
        if (anno == null) {
            return null;
        }
        return generatorMetaField(anno.name(), anno.length(), anno.primaryKey(), anno.allowNull(), anno.unique());
    }

}
