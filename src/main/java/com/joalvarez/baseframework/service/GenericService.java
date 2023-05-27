package com.joalvarez.baseframework.service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface GenericService<DTO, PK> {

    List<DTO> getAll();
    DTO get(PK id);
    DTO save(DTO entity);
    DTO update(DTO entity);
    void delete(DTO entity);
    void deleteById(PK id);
}
