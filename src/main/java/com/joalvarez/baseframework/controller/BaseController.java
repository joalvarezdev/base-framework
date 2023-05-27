package com.joalvarez.baseframework.controller;

import com.joalvarez.baseframework.data.dao.GenericDAO;
import com.joalvarez.baseframework.data.dto.BaseDTO;
import com.joalvarez.baseframework.data.mapper.GenericMapper;
import com.joalvarez.baseframework.service.BaseService;
import com.joalvarez.baseframework.utils.HasLogger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseController<SER extends BaseService<DAO, MAP, DTO, ENT, PK>, DAO extends GenericDAO<ENT, PK>, MAP extends GenericMapper<DTO, ENT>, DTO extends BaseDTO, ENT, PK> extends BasicController<SER, DAO, MAP, DTO, ENT, PK> implements HasLogger {

    public BaseController(SER service) {
        super(service);
    }

    @GetMapping
    public ResponseEntity<List<DTO>> getAll() {
        return this.getAll(this.service);
    }

    @GetMapping("{id}")
    public ResponseEntity<DTO> get(@RequestParam PK id) {
        return this.get(id);
    }

    @PostMapping
    public ResponseEntity<DTO> save(@RequestBody DTO dto) {
        return this.save(this.service, dto);
    }

    @PutMapping
    public ResponseEntity<DTO> update(@RequestBody DTO dto) {
        return this.update(this.service, dto);
    }

    @DeleteMapping
    public void delete(@RequestBody DTO dto) {
        this.delete(this.service, dto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@RequestParam PK id) {
        this.deleteById(this.service, id);
    }
}
