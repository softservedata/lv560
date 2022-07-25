package com.project.soft.util;

import org.springframework.validation.FieldError;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomBindingResult<T> {

    private Map<String, List<FieldError>> fieldErrorMap;

    private Class<T> domainClass;

    public CustomBindingResult(Class<T> cls) {
        this.domainClass = cls;
        initializeErrorsMap();
    }

    private void initializeErrorsMap() {
        Field[] fields = domainClass.getFields();

        for (Field field : fields) {
            fieldErrorMap.put(field.getName(), new ArrayList<>());
        }
    }

    public void add(FieldError error) {
        String field = error.getField();
        fieldErrorMap.get(field).add(error);
    }

    public FieldError getFieldError(String field) {
        return fieldErrorMap.get(field).get(0);
    }

    public List<FieldError> getFieldErrors(String field) {
        return fieldErrorMap.get(field);
    }

    public boolean hasFieldErrors(String field) {
        return !fieldErrorMap.get(field).isEmpty();
    }

    public boolean hasErrors() {
        boolean hasErrors = false;

        for (Map.Entry<String, List<FieldError>> entry : fieldErrorMap.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                hasErrors = true;
                break;
            }
        }

        return hasErrors;
    }
}
