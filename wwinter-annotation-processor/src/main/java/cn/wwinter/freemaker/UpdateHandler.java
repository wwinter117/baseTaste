package cn.wwinter.freemaker;

import cn.wwinter.model.MetaField;
import cn.wwinter.model.TableInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UpdateHandler
 * Package: cn.wwinter.freemaker
 * Description:
 * Datetime: 2023/11/9
 * Author: zhangdd
 */
public class UpdateHandler extends AbstractTemplateHandler implements GetSqlHandler{
    @Override
    public String handle(TableInfo tableInfo) {
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableInfo.getTableName());
        List<Map<String, Object>> fields = new ArrayList<>();
        List<String> fieldValues = new ArrayList<>();
        for (MetaField field : tableInfo.getFields()) {
            Map<String, Object> fieldsMap = new HashMap<>();
            fieldsMap.put("name", field.getName());
            fields.add(fieldsMap);
            fieldValues.add("2");
        }
        map.put("fields", fields);
        map.put("field_index", 0); // 替换成实际的索引值
        map.put("fieldValues", fieldValues);
        map.put("primaryKey", "primaryKey");
        map.put("primaryKeyValue", 2);
        return generateSql(map, "sql_update.ftl");
    }

}
