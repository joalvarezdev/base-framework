package com.joalvarez.baseframework.controller;

import com.joalvarez.baseframework.service.GenericService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenericController<SER extends GenericService<DTO, PK>, DTO, PK> {

    default ResponseEntity<List<DTO>> getAll(SER service) {
        return ResponseEntity.ok(service.getAll());
    }

    default ResponseEntity<DTO> get(SER service, PK id) {
        return ResponseEntity.ok(service.get(id));
    }

    default ResponseEntity<DTO> save(SER service, DTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    default void delete(SER service, DTO dto) {
        service.delete(dto);
    }

    default void deleteById(SER service, PK id) {
        service.deleteById(id);
    }

    default ResponseEntity<DTO> update(SER service, DTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }
}
