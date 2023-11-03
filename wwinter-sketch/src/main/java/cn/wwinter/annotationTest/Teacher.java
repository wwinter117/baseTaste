package cn.wwinter.annotationTest;

import cn.wwinter.annotations.*;

import java.util.Date;

/**
 * ClassName: User
 * Package: cn.wwinter.annotationTest
 * Description:
 * Datetime: 2023/10/30
 * Author: zhangdd
 */
@DBTable(name = "teacher")
public class Teacher {

    @SQLLong(name = "id", length = 20, primaryKey = true, comment = "主键id")
    private Long id;
    @SQLString(name = "name", length = 20, comment = "姓名")
    private String name;
    @SQLInteger(name = "age", length = 5, comment = "年龄")
    private Integer age;
    @SQLDate(name = "birthDay", comment = "生日")
    private Date birthDay;
}
