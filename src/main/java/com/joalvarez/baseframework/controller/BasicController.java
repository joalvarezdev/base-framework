package com.joalvarez.baseframework.controller;

import com.joalvarez.baseframework.data.dao.GenericDAO;
import com.joalvarez.baseframework.data.dto.BaseDTO;
import com.joalvarez.baseframework.data.mapper.GenericMapper;
import com.joalvarez.baseframework.service.BaseService;

public class BasicController<SER extends BaseService<DAO, MAP, DTO, ENT, PK>, DAO extends GenericDAO<ENT, PK>, MAP extends GenericMapper<DTO, ENT>, DTO extends BaseDTO, ENT, PK> implements GenericController<SER, DTO, PK>{

    protected SER service;

    public BasicController(SER service) {
        this.service = service;
    }
}
