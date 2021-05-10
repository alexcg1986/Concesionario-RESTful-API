package com.teknei.concesionario.services;

import com.teknei.concesionario.common.GenericCrudServiceImpl;
import com.teknei.concesionario.dto.ModeloDTO;
import com.teknei.concesionario.model.Modelo;
import com.teknei.concesionario.repository.ModeloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ModeloServiceImpl extends GenericCrudServiceImpl<Modelo, ModeloDTO, Integer> implements ModeloService {

    @Autowired
    private ModeloRepository repository;

    @Override
    public JpaRepository<Modelo, Integer> getRepository() {
        return repository;
    }
}