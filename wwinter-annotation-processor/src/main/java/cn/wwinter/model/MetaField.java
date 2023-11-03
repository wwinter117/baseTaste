package cn.wwinter.model;

import cn.wwinter.enums.JavaType;
import cn.wwinter.enums.JdbcType;

import javax.lang.model.element.TypeElement;

/**
 * ClassName: MetaField
 * Package: cn.wwinter.model
 * Description:
 * Datetime: 2023/11/2
 * Author: zhangdd
 */
public class MetaField {
    private String name;
    private JavaType javaType;
    private JdbcType jdbcType;
    private int length;
    boolean primaryKey;
    boolean allowNull;
    boolean unique;
    private String comment;

    public MetaField() {
    }

    public MetaField(String name, JavaType javaType, JdbcType jdbcType, int length, boolean primaryKey, boolean allowNull, boolean unique, String comment) {
        this.name = name;
        this.javaType = javaType;
        this.jdbcType = jdbcType;
        this.length = length;
        this.primaryKey = primaryKey;
        this.allowNull = allowNull;
        this.unique = unique;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JavaType getJavaType() {
        return javaType;
    }

    public void setJavaType(JavaType javaType) {
        this.javaType = javaType;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(JdbcType jdbcType) {
        this.jdbcType = jdbcType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isAllowNull() {
        return allowNull;
    }

    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", javaType=" + javaType +
                ", jdbcType=" + jdbcType +
                ", length=" + length +
                ", primaryKey=" + primaryKey +
                ", allowNull=" + allowNull +
                ", unique=" + unique +
                ", comment=" + comment +
                '}';
    }
}
