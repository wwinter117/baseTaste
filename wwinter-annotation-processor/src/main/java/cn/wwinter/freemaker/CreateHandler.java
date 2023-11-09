package cn.wwinter.freemaker;

import cn.wwinter.model.TableInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: CreateHandler
 * Package: cn.wwinter.freemaker
 * Description:
 * Datetime: 2023/11/9
 * Author: zhangdd
 */
public class CreateHandler extends AbstractTemplateHandler implements GetSqlHandler{
    @Override
    public String handle(TableInfo tableInfo) {
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableInfo.getTableName());
        map.put("fields", tableInfo.getFields());
        return generateSql(map, "sql_create.ftl");
    }
}
