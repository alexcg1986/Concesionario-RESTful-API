package com.teknei.concesionario.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.teknei.concesionario.common.GenericCrudServiceImpl;
import com.teknei.concesionario.dto.CocheDTO;
import com.teknei.concesionario.dto.ParametrosDTO;
import com.teknei.concesionario.model.Coche;
import com.teknei.concesionario.model.QCoche;
import com.teknei.concesionario.repository.CocheRepository;
import com.teknei.concesionario.util.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CocheServiceImpl extends GenericCrudServiceImpl<Coche, CocheDTO, Integer> implements CocheService {

    @Autowired
    private CocheRepository repository;

    @Autowired
    private Mapper<Coche, CocheDTO> mapper;

    @Override
    public JpaRepository<Coche, Integer> getRepository() {
        return repository;
    }

    @Override
    public List<CocheDTO> getAllFiltered(ParametrosDTO parametrosDTO) {
        final BooleanExpression booleanExpression = QCoche.coche.marca.id.eq(parametrosDTO.getMarcaId());
        final OrderSpecifier<Integer> orderSpecifier = QCoche.coche.id.asc();
        return StreamSupport.stream(repository.findAll(booleanExpression, orderSpecifier).spliterator(), false)
                .map(mapper::toDTO).collect(Collectors.toList());
    }
}