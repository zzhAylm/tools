package com.zzh.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/13 19:27
 */
public class ValidateImp implements ConstraintValidator<ValidateAnn,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
