package ru.polytech.mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;

import java.lang.reflect.Type;

public interface Mapper {

    <D> D map(Object source, Class<D> destinationType);

    <D> D map(Object source, Class<D> destinationType, String typeMapName);

    void map(Object source, Object destination);

    void map(Object source, Object destination, String typeMapName);

    <D> D map(Object source, Type destinationType);

    <D> D map(Object source, Type destinationType, String typeMapName);

    <T> T readValue(String content, Class<T> valueType) throws Exception;

    <T> T readValue(String content, TypeReference<T> valueTypeRef) throws Exception;

    <T> T readValue(String content, JavaType valueType) throws Exception;

}
