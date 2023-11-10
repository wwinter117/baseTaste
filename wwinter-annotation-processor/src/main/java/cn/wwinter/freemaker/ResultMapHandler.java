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
public class ResultMapHandler extends AbstractTemplateHandler implements GetSqlHandler{
    @Override
    public String handle(TableInfo tableInfo) {
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableInfo.getTableName());
        List<Map<String, Object>> fields = new ArrayList<>();
        for (MetaField field : tableInfo.getFields()) {
            Map<String, Object> valuesMap = new HashMap<>();
            valuesMap.put("name", field.getName());
            valuesMap.put("tableName", field.getTableName());
            valuesMap.put("jdbcType", field.getJdbcType());
            valuesMap.put("javaType", field.getJavaType());
            fields.add(valuesMap);
        }
        map.put("fields", fields);
        return generateSql(map, "sql_resultMap.ftl");
    }

}
