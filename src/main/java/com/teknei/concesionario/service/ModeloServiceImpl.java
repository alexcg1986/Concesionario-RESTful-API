package com.teknei.concesionario.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.teknei.concesionario.common.GenericCrudServiceImpl;
import com.teknei.concesionario.dto.ModeloDTO;
import com.teknei.concesionario.dto.ParametrosDTO;
import com.teknei.concesionario.model.Modelo;
import com.teknei.concesionario.model.QModelo;
import com.teknei.concesionario.repository.ModeloRepository;
import com.teknei.concesionario.util.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ModeloServiceImpl extends GenericCrudServiceImpl<Modelo, ModeloDTO, Integer> implements ModeloService {

    @Autowired
    private ModeloRepository repository;

    @Autowired
    private Mapper<Modelo, ModeloDTO> mapper;

    @Override
    public JpaRepository<Modelo, Integer> getRepository() {
        return repository;
    }

    @Override
    public List<ModeloDTO> getAllFiltered(ParametrosDTO parametrosDTO) {
        BooleanExpression booleanExpression = QModelo.modelo.marca.id.eq(parametrosDTO.getMarcaId());
        OrderSpecifier<Integer> orderSpecifier = QModelo.modelo.id.asc();
        return StreamSupport.stream(repository.findAll(booleanExpression, orderSpecifier).spliterator(), false)
                .map(mapper::toDTO).collect(Collectors.toList());
    }
}