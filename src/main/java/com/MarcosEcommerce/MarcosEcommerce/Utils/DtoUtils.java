package com.MarcosEcommerce.MarcosEcommerce.Utils;

import com.MarcosEcommerce.MarcosEcommerce.Exceptions.EmptyDtoException;

import java.lang.reflect.Field;

public  class DtoUtils {
    public static void isEmpty(Object dto) {

        try {
            for (Field field :  dto.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(dto);
                System.out.println(value);
                if (value != null) {
                    return;
                }
            }
    } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
    }
     throw new EmptyDtoException("Payload cannot be empty. All fields are null");
    }
}
