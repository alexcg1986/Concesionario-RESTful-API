package com.teknei.concesionario.repository;

import com.teknei.concesionario.model.Modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer>, QuerydslPredicateExecutor<Modelo> {
}