package com.joalvarez.baseframework.data.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<ENT, PK> {

    Optional<ENT> findById(PK id);
    List<ENT> findAll();
    ENT update(ENT entity);
    ENT save(ENT entity);
    void delete(ENT entity);
    void deleteById(PK id);
}
