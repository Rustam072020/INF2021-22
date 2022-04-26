package ru.itis.hotel.validation.annotation;

import ru.itis.hotel.validation.validator.AgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAge {

    String message() default "just for adult";

    int age() default 18;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default  {};
}
