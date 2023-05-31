package com.joalvarez.baseframework.service;

import com.joalvarez.baseframework.data.dao.GenericDAO;
import com.joalvarez.baseframework.data.mapper.GenericMapper;
import com.joalvarez.baseframework.utils.HasLogger;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public class BaseService<DAO extends GenericDAO<ENT, PK>, MAP extends GenericMapper<DTO, ENT>, DTO extends Serializable, ENT, PK> implements GenericService<DTO, PK>, HasLogger {

    protected DAO dao;
    protected MAP mapper;

    public BaseService(DAO dao, MAP mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public List<DTO> getAll() {
        return this.mapper.toDTOS(this.dao.findAll());
    }

    @Override
    public DTO get(PK id) {
        return this.mapper.toDTO(
                this.dao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("An error occurred when trying to obtain entity! The specified ID %s do not exists.", id)))
        );
    }

    @Override
    public DTO save(DTO entity) {
        ENT ent = this.mapper.fromDTO(entity);
        try {
            return this.mapper.toDTO(this.dao.save(ent));
        } catch (DataIntegrityViolationException exp) {
            throw new PersistenceException(String.format("An error occurred when trying to create! The specified entity %s contains an error. Exception: %s", entity.getClass().getSimpleName(), exp.getMessage()));
        }
    }

    @Override
    public DTO update(DTO entity) {
        ENT ent = this.mapper.fromDTO(entity);
        try {
            return this.mapper.toDTO(this.dao.save(ent));
        } catch (DataIntegrityViolationException exp) {
            throw new PersistenceException(String.format("An error occurred when trying to update! The specified entity %s contains an error. Exception: %s", entity.getClass().getSimpleName(), exp.getMessage()));
        }
    }

    @Override
    public void delete(DTO entity) {
        try {
            this.dao.delete(this.mapper.fromDTO(entity));
        } catch (EmptyResultDataAccessException exp) {
            throw new EntityNotFoundException(String.format("An error occurred when trying to delete! The specified entity %s does not exist. Exception: %s", entity.getClass().getSimpleName(), exp.getMessage()));
        }
    }

    @Override
    public void deleteById(PK id) {
        try {
            this.dao.deleteById(id);
        } catch (EmptyResultDataAccessException exp) {
            throw new EntityNotFoundException(String.format("An error occurred when trying to delete! The specified ID %s does not exist. Exception: %s", id, exp.getMessage()));
        }
    }
}
