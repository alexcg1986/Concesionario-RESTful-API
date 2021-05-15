package com.teknei.concesionario.service;

import com.teknei.concesionario.common.GenericCrudServiceImpl;
import com.teknei.concesionario.dto.MarcaDTO;
import com.teknei.concesionario.model.Marca;
import com.teknei.concesionario.repository.MarcaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl extends GenericCrudServiceImpl<Marca, MarcaDTO, Integer> implements MarcaService {

    @Autowired
    private MarcaRepository repository;

    @Override
    public JpaRepository<Marca, Integer> getRepository() {
        return repository;
    }
}