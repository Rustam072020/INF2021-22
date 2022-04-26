package ru.itis.hotel.validation.validator;

import ru.itis.hotel.validation.annotation.ValidAge;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AgeValidator implements ConstraintValidator<ValidAge, LocalDate> {

    private Integer age;

    @Override
    public void initialize(ValidAge constraintAnnotation) {
        this.age = constraintAnnotation.age();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate.isBefore(LocalDate.now().minusYears(age));
    }
}
