package com.sulzer.sulzertoolapp.tool.validator;

import com.sulzer.sulzertoolapp.tool.ToolRepository;
import com.sulzer.sulzertoolapp.tool.annotation.UniqueAtmsNumber;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueToolAtmsNumberValidator implements ConstraintValidator<UniqueAtmsNumber, String> {

    @Autowired
    private ToolRepository toolRepository;

    @Override
    public boolean isValid(String toolAtmsNumber, ConstraintValidatorContext constraintValidatorContext) {
        return !toolRepository.existsByToolAtmsNumber(toolAtmsNumber);
    }
}
