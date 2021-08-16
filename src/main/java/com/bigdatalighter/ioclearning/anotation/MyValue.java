package com.bigdatalighter.ioclearning.anotation;

import java.lang.annotation.*;

/**
 * Description:
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyValue {

    public String values();

}
