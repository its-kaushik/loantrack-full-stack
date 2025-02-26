package com.example.loantrack.validation.phoneNumber;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumber {
    String message() default "Invalid phone number format. It must be exactly 10 digits.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
