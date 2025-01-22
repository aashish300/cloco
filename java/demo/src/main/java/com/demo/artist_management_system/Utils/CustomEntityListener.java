package com.example.demo.Utils;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CustomEntityListener {

    @PrePersist
    @PreUpdate
    public void trimStrings(Object entity) {
        if(entity == null) return;
        for(Field field : getAllFields(entity.getClass())) {
            if(field.getType().equals(String.class)) {
                field.setAccessible(true);
                try{
                    String value = (String) field.get(entity);
                    if(value != null) {
                        field.set(entity, value.trim());
                    }
                }catch (Exception e) {
                    log.error("[x] Exception occurred while trimming strings");
                }
            }
        }
    }

    private List<Field> getAllFields(Class<?> aClass) {
        List<Field> fields = new ArrayList<>();
        while(aClass != null && aClass != Object.class) {
            fields.addAll(Arrays.asList(aClass.getDeclaredFields()));
            aClass = aClass.getSuperclass();
        }
        return fields;
    }
}
