package com.teknei.concesionario.repository;

import com.teknei.concesionario.model.Coche;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Integer>, QuerydslPredicateExecutor<Coche> {
}