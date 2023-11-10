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
public class SqlFieldsHandler extends AbstractTemplateHandler implements GetSqlHandler{
    @Override
    public String handle(TableInfo tableInfo) {
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableInfo.getTableName());
        List<String> fields = new ArrayList<>();
        for (MetaField field : tableInfo.getFields()) {
            fields.add(field.getTableName());
        }
        map.put("fields", fields);
        return generateSql(map, "sql_fields.ftl");
    }

}
