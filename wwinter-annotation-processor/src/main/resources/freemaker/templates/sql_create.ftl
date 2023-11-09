<#-- 建表语句 -->
CREATE TABLE `${tableName}` (
<#list fields as field>
    `${field.name}` ${field.jdbcType}<#if field.length??>(${field.length})</#if><#if field.primaryKey> PRIMARY KEY</#if><#if field.allowNull> NOT NULL</#if><#if field.unique> UNIQUE</#if><#if field.comment??> COMMENT '${field.comment}'</#if>,
</#list>
);
