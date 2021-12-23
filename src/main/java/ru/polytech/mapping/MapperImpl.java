package ru.polytech.mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Type;

@Component
@Qualifier("mapper-api")
public class MapperImpl implements Mapper {

    private ModelMapper modelMapper;

    private final ObjectMapper objectMapper;

    @Autowired
    public MapperImpl() {
        this.objectMapper = new ObjectMapper();
    }

    @PostConstruct
    public void init() {
        modelMapper = new ModelMapper();
    }

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        if (source == null) {
            return null;
        }
        return modelMapper.map(source, destinationType);
    }

    @Override
    public <D> D map(Object source, Class<D> destinationType, String typeMapName) {
        return modelMapper.map(source, destinationType, typeMapName);
    }

    @Override
    public void map(Object source, Object destination) {
        if (source == null) {
            return;
        }
        modelMapper.map(source, destination);
    }

    @Override
    public void map(Object source, Object destination, String typeMapName) {
        modelMapper.map(source, destination, typeMapName);
    }

    @Override
    public <D> D map(Object source, Type destinationType) {
        return modelMapper.map(source, destinationType);
    }

    @Override
    public <D> D map(Object source, Type destinationType, String typeMapName) {
        return modelMapper.map(source, destinationType, typeMapName);
    }

    @Override
    public <T> T readValue(String content, Class<T> valueType) throws Exception {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            throw new Exception("Ошибка обработки данных");
        }
    }

    @Override
    public <T> T readValue(String content, TypeReference<T> valueTypeRef) throws Exception {
        try {
            return objectMapper.readValue(content, valueTypeRef);
        } catch (IOException e) {
            throw new Exception("Ошибка обработки данных");
        }
    }

    @Override
    public <T> T readValue(String content, JavaType valueType) throws Exception {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            throw new Exception("Ошибка обработки данных");
        }
    }

}
