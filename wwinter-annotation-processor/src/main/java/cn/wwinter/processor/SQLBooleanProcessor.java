package cn.wwinter.processor;

import cn.wwinter.annotations.SQLBoolean;
import cn.wwinter.annotations.SQLDecimal;
import cn.wwinter.model.MetaField;

import javax.lang.model.element.Element;

/**
 * ClassName: SQLStringProcessor
 * Package: cn.wwinter.processor
 * Description:
 * Datetime: 2023/11/1
 * Author: zhangdd
 */
public class SQLBooleanProcessor implements SQLElementProcessor {

    @Override
    public MetaField process(Element field) {
        SQLBoolean anno = field.getAnnotation(SQLBoolean.class);
        if (anno == null) {
            return null;
        }
        return generatorMetaField(anno.name(), anno.length(), anno.primaryKey(), anno.allowNull(), anno.unique());
    }

}
