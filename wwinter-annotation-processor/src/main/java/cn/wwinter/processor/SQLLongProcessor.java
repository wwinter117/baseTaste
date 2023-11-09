package cn.wwinter.processor;

import cn.wwinter.annotations.SQLLong;
import cn.wwinter.enums.JavaType;
import cn.wwinter.enums.JdbcType;
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
        MetaField metaField = generatorMetaField(anno.name(), anno.length(), anno.primaryKey(), anno.allowNull(), anno.unique(), anno.comment());
        metaField.setJavaType(JavaType.LONG);
        metaField.setJdbcType(JdbcType.INT);
        return metaField;
    }
}
