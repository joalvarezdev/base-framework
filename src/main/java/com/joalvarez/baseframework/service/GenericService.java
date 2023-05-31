package com.joalvarez.baseframework.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface GenericService<DTO, PK> {

    List<DTO> getAll();
    DTO get(PK id);
    DTO save(DTO entity);
    DTO update(DTO entity);
    void delete(DTO entity);
    void deleteById(PK id);
}
