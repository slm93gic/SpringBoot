package com.slm.anno;

import java.lang.annotation.*;

/**
 * @author limin shen
 * @date 2023-09-01 15:08
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyServiceAnno {
    String value() default "";
}
