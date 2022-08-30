package com.sulzer.sulzertoolapp.tool.validation.validator;

import com.sulzer.sulzertoolapp.tool.Tool;
import com.sulzer.sulzertoolapp.tool.ToolRepository;
import com.sulzer.sulzertoolapp.tool.validation.annotation.Unique;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private String idField;
    private String uniqueField;

    @Autowired
    private ToolRepository toolRepository;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.idField = constraintAnnotation.idField();
        this.uniqueField = constraintAnnotation.uniqueField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Long id = (Long) new BeanWrapperImpl(value)
                .getPropertyValue(idField);

        Object uniqueValue = new BeanWrapperImpl(value)
                .getPropertyValue(uniqueField);

        if (value instanceof Tool) {
           return switch (uniqueField) {
                case "toolNumber" -> !toolRepository.existsByToolNumber((String) uniqueValue, id);
                case "toolAtmsNumber" -> !toolRepository.existsByToolAtmsNumber((String) uniqueValue, id);
                default -> false;
            };
        }

        return false;
    }
}
