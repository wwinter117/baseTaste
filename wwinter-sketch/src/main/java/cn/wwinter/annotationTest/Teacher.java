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

    @SQLLong(name = "id", length = 20, primaryKey = true)
    private Long id;
    @SQLString(name = "name", length = 20)
    private String name;
    @SQLInteger(name = "age", length = 5)
    private Integer age;
    @SQLDate(name = "birthDay")
    private Date birthDay;
}
