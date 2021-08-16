package com.bigdatalighter.ioclearning.anotation;

import java.lang.annotation.*;

/**
 *
 * Description:
 * 定义：自动绑定注解
 * 作用：用于字段注解，作用是告诉容器哪个字段需要进行依赖注入
 *
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface MyAutowired {

    String value() default "";

}
