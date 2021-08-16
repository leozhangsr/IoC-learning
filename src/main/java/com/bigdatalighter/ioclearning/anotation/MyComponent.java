package com.bigdatalighter.ioclearning.anotation;

import java.lang.annotation.*;

/**
 *
 *  Description:
 * 定义：组件注解
 * 作用：用于类标注，告诉容器被注解类是目标组件，需要进行对象创建-关联托管
 *
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyComponent {

    String values() default "";

}
