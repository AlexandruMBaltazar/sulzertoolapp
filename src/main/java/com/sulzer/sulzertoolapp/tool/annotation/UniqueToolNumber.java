package com.sulzer.sulzertoolapp.tool.annotation;

import com.sulzer.sulzertoolapp.tool.validator.UniqueToolNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueToolNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueToolNumber {
    String message() default "Tool Number is already registered";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
