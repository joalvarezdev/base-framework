package com.joalvarez.baseframework.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<ENT, PK> extends JpaRepository<ENT, PK> {
}
