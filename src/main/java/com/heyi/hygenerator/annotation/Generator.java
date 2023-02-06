package com.heyi.hygenerator.annotation;

import com.heyi.hygenerator.strategy.STString;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Generator {

    String value() default "%s";

    Class type() default STString.class;

    int length() default 0;

}
