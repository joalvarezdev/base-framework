package com.joalvarez.baseframework.data.mapper;

import java.io.Serializable;
import java.util.List;

public interface GenericMapper<DTO extends Serializable, ENT> {
    DTO toDTO(ENT entity);

    ENT fromDTO(DTO entity);

    List<DTO> toDTOS(List<ENT> entities);

    List<ENT> fromDTOS(List<DTO> entities);

    DTO fromString(String serialized);
}
