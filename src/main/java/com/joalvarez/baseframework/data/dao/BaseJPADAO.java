package com.joalvarez.baseframework.data.dao;

import com.joalvarez.baseframework.data.repository.GenericRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class BaseJPADAO<REP extends GenericRepository<ENT, PK>, ENT, PK> implements GenericDAO<ENT, PK> {

    private REP repository;

    public BaseJPADAO(REP repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ENT> findById(PK id) {
        return this.repository.findById(id);
    }

    @Override
    public List<ENT> findAll() {
        return this.repository.findAll();
    }

    @Override
    public ENT update(ENT entity) {
        return this.repository.save(entity);
    }

    @Override
    public ENT save(ENT entity) {
        return this.repository.save(entity);
    }

    @Override
    public void delete(ENT entity) {
        this.repository.delete(entity);
    }

    @Override
    public void deleteById(PK id) {
        this.repository.deleteById(id);
    }

    public REP getRepository() {
        return this.repository;
    }

}
