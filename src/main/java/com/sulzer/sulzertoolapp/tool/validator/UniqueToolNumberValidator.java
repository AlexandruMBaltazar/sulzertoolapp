package com.sulzer.sulzertoolapp.tool.validator;

import com.sulzer.sulzertoolapp.tool.ToolRepository;
import com.sulzer.sulzertoolapp.tool.annotation.UniqueToolNumber;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueToolNumberValidator implements ConstraintValidator<UniqueToolNumber, String> {

    @Autowired
    private ToolRepository toolRepository;

    @Override
    public boolean isValid(String toolNumber, ConstraintValidatorContext constraintValidatorContext) {
        return !toolRepository.existsByToolNumber(toolNumber);
    }
}
