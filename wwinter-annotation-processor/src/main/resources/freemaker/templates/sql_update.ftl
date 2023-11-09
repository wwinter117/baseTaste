<#-- 更新语句 -->
UPDATE ${tableName} SET
<#list fields as field>
    ${field.name} = ${fieldValues[field_index]}<#if field_has_next>,</#if>
</#list>
WHERE ${primaryKey} = ${primaryKeyValue};
