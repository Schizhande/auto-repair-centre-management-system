package com.schizhande.autorepaircentremanagementsystem.commons.object_convertor;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectMap {

    public static <T, U> U mapToEntity(T input, Class<U> mappedClass){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.convertValue(input, mappedClass);
    }

    public static <T , U> U updateEntity(T input, Class<U> mappedClass) throws JsonMappingException {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.SKIP))
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return (U) objectMapper.updateValue(mappedClass,input);
        }catch (JsonMappingException ex){
            log.error("---> Failed to map Objects ", ex);
            throw new IllegalStateException("Failed to map the objects");
        }

    }

}
