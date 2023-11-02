package cn.wwinter.model;

import java.util.List;

/**
 * ClassName: TableInfo
 * Package: cn.wwinter.model
 * Description:
 * Datetime: 2023/11/2
 * Author: zhangdd
 */
public class TableInfo {
    private String tableName;
    private List<MetaField> fields;

    public TableInfo() {
    }

    public TableInfo(String tableName, List<MetaField> fields) {
        this.tableName = tableName;
        this.fields = fields;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<MetaField> getFields() {
        return fields;
    }

    public void setFields(List<MetaField> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MetaField field : fields) {
            sb.append("\n").append("      ").append(field);
        }
        return "{\n" +
                "   tableName = " + tableName + "\n" +
                "   fields = " + sb + "\n" +
                '}';
    }
}
