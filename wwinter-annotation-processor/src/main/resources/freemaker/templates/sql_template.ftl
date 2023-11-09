<#-- 建表语句 -->
CREATE TABLE ${tableName} (
<#list fields as field>
    ${field.name} ${field.type}<#if field.length?exists>( ${field.length} )</#if><#if field.primaryKey> PRIMARY KEY</#if><#if field.notNull> NOT NULL</#if><#if field.unique> UNIQUE</#if>,
</#list>
);

<#-- 插入语句 -->
INSERT INTO ${tableName} (${fieldNames}) VALUES (${fieldValues});

<#-- 更新语句 -->
UPDATE ${tableName} SET
<#list fields as field>
    ${field.name} = ${fieldValues[field_index]}<#if field_has_next>,</#if>
</#list>
WHERE ${primaryKey} = ${primaryKeyValue};

<#-- 删除语句 -->
DELETE FROM ${tableName} WHERE ${primaryKey} = ${primaryKeyValue};
