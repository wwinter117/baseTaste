package cn.wwinter.processor;

import cn.wwinter.annotations.SQLString;
import cn.wwinter.model.MetaField;
import com.sun.tools.javac.code.Attribute;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;

/**
 * ClassName: SQLStringProcessor
 * Package: cn.wwinter.processor
 * Description:
 * Datetime: 2023/11/1
 * Author: zhangdd
 */
public class SQLStringProcessor implements SQLElementProcessor {

    @Override
    public MetaField process(Element field) {
        SQLString anno = field.getAnnotation(SQLString.class);
        if (anno == null) {
            return null;
        }
        return generatorMetaField(anno.name(), anno.length(), anno.primaryKey(), anno.allowNull(), anno.unique(), anno.comment());
    }

}
