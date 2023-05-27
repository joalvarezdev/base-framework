package com.joalvarez.baseframework.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.baseframework.utils.HasLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseMapper <DTO extends Serializable, ENT> implements GenericMapper<DTO, ENT>, HasLogger {
    private Class<DTO> dtoClass;
    private Class<ENT> domainClass;
    private ObjectMapper mapper;

    public BaseMapper() {
        Class<?>[] arguments = GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseMapper.class);
        if (arguments != null && arguments.length > 0) {
            this.dtoClass = (Class<DTO>) arguments[0];
            this.domainClass = (Class<ENT>) arguments[1];
        }

    }

    @Override
    public DTO toDTO(ENT entity) {
        return this.mapper.convertValue(entity, this.dtoClass);
    }

    @Override
    public ENT fromDTO(DTO entity) {
        return this.mapper.convertValue(entity, this.domainClass);
    }

    @Override
    public List<DTO> toDTOS(List<ENT> entities) {
        List<DTO> mapped = new ArrayList<>();
        Iterator<ENT> var3 = entities.iterator();

        while (var3.hasNext()) {
            ENT entity = var3.next();
            mapped.add(this.toDTO(entity));
        }

        return mapped;
    }

    @Override
    public List<ENT> fromDTOS(List<DTO> entities) {
        List<ENT> mapped = new ArrayList<>();
        Iterator<DTO> var3 = entities.iterator();

        while (var3.hasNext()) {
            DTO entity = var3.next();
            mapped.add(this.fromDTO(entity));
        }

        return mapped;
    }

    @Override
    public DTO fromString(String serialized) {
        try {
            return this.mapper.readValue(serialized, this.dtoClass);
        } catch (IOException var3) {
            this.error(var3.getMessage(), var3);
            return null;
        }
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
}