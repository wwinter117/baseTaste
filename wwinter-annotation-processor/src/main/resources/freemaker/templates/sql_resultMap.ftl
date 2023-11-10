<resultMap id="" type="">
<#list fields as field>
    <result column="${field.tableName}" property="${field.name}" jdbcType="${field.jdbcType}" javaType="${field.javaType}"/>
</#list>
</resultMap>
