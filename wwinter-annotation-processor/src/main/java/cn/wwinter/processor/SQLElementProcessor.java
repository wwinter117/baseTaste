package cn.wwinter.processor;

import cn.wwinter.model.MetaField;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;

/**
 * ClassName: SQLElementProcessor
 * Package: cn.wwinter.processor
 * Description:
 * Datetime: 2023/11/1
 * Author: zhangdd
 */
public interface SQLElementProcessor extends ElementProcessor<Element, MetaField> {

    default MetaField generatorMetaField(
            String name, int length, boolean primaryKey, 
            boolean allowNull, boolean unique) {
        MetaField metaField = new MetaField();
        metaField.setName(name);
        metaField.setLength(length);
        metaField.setPrimaryKey(primaryKey);
        metaField.setUnique(unique);
        metaField.setAllowNull(allowNull);
        return metaField;
    }
}
