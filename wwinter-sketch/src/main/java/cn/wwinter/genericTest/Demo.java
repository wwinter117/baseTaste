package cn.wwinter.genericTest;

import cn.wwinter.annotationTest.User;

import java.util.Arrays;

/**
 * ClassName: Demo
 * Package: cn.wwinter.genericTest
 * Description:
 * Datetime: 2023/11/4
 * Author: zhangdd
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println("User.class.getTypeParameters() = " + Arrays.toString(User.class.getTypeParameters()));
        System.out.println("Factory.class.getTypeParameters() = " + Arrays.toString(Factory.class.getTypeParameters()));
    }
}
