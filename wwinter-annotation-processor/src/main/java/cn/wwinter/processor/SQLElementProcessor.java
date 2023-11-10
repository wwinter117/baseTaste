package cn.wwinter.processor;

import cn.wwinter.model.MetaField;

import javax.lang.model.element.Element;

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
            boolean allowNull, boolean unique, String comment) {
        MetaField metaField = new MetaField();
        metaField.setName(name);
        metaField.setTableName(getTableName(name));
        metaField.setLength(length);
        metaField.setPrimaryKey(primaryKey);
        metaField.setUnique(unique);
        metaField.setAllowNull(allowNull);
        metaField.setComment(comment);
        return metaField;
    }

    default String getTableName(String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append('_');
            }
            sb.append(c);
        }
        return sb.toString().toLowerCase();
    }
}
