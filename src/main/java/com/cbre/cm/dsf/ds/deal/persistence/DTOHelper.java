package com.cbre.cm.dsf.ds.deal.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public class DTOHelper {
    private static final Logger logger = LoggerFactory.getLogger(DTOHelper.class);

    public static void copyModelToDTO2(Object copyFrom, Object copyTo) {
        Optional<Boolean> result = Arrays.stream(copyTo.getClass().getMethods())
                .filter(method -> method.getName().startsWith("set"))
                .map((copyToSetter) -> {
                    String getterName = copyToSetter.getName().replaceFirst(Pattern.quote("set"), "get");
                    try {
                        Method copyFromGetter = copyFrom.getClass().getMethod(getterName);
                        copyToSetter.invoke(copyTo, copyFromGetter.invoke(copyFrom));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                })
                .reduce((a, b) -> a && b);
    }

    public static void copyModelToDTO(Object copyFrom, Object copyTo) {
        Arrays.stream(copyTo.getClass().getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .map(field -> {
                    try {
                        if(!isAutoCopyIgnore(field)) {

                            Method copyFromGetter = null;
                            try {
                                copyFromGetter = copyFrom.getClass().getMethod("get" + capitalizeFirstLetter(field.getName()));
                            } catch (Exception e) {
                                logger.debug("No method found"+e.getMessage());
                            }
                            if(copyFromGetter != null) {
                                Method copyToSetter = copyTo.getClass().getMethod("set" + capitalizeFirstLetter(field.getName()), field.getType());
                                copyToSetter.invoke(copyTo, copyFromGetter.invoke(copyFrom));
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                })
                .reduce((a, b) -> a && b);

    }

    private static boolean isAutoCopyIgnore(Field field) {
        IgnoreAutoCopy fieldAnnotation = field.getAnnotation(IgnoreAutoCopy.class);
        return fieldAnnotation!=null ? fieldAnnotation.value():false;
    }

    private static String capitalizeFirstLetter(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
