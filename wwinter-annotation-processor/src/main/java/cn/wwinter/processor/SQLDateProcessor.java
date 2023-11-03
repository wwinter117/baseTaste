package cn.wwinter.processor;

import cn.wwinter.annotations.SQLDate;
import cn.wwinter.model.MetaField;

import javax.lang.model.element.Element;

/**
 * ClassName: SQLStringProcessor
 * Package: cn.wwinter.processor
 * Description:
 * Datetime: 2023/11/1
 * Author: zhangdd
 */
public class SQLDateProcessor implements SQLElementProcessor {

    @Override
    public MetaField process(Element field) {
        SQLDate anno = field.getAnnotation(SQLDate.class);
        if (anno == null) {
            return null;
        }
        return generatorMetaField(anno.name(), anno.length(), anno.primaryKey(), anno.allowNull(), anno.unique(), anno.comment());
    }

}
