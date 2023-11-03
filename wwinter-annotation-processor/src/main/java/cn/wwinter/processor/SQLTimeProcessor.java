package cn.wwinter.processor;

import cn.wwinter.annotations.SQLInteger;
import cn.wwinter.annotations.SQLTime;
import cn.wwinter.model.MetaField;

import javax.lang.model.element.Element;

/**
 * ClassName: SQLStringProcessor
 * Package: cn.wwinter.processor
 * Description:
 * Datetime: 2023/11/1
 * Author: zhangdd
 */
public class SQLTimeProcessor implements SQLElementProcessor {

    @Override
    public MetaField process(Element field) {
        SQLTime anno = field.getAnnotation(SQLTime.class);
        if (anno == null) {
            return null;
        }
        return generatorMetaField(anno.name(), anno.length(), anno.primaryKey(), anno.allowNull(), anno.unique(), anno.comment());
    }

}
