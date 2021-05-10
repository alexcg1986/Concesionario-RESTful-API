package com.teknei.concesionario.services;

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
import com.teknei.concesionario.utils.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CocheServiceImpl extends GenericCrudServiceImpl<Coche, CocheDTO, Integer> implements CocheService {

    @Autowired
    private CocheRepository repository;

    @Autowired
    Mapper<Coche, CocheDTO> mapper;

    @Override
    public JpaRepository<Coche, Integer> getRepository() {
        return repository;
    }

    @Override
    public List<CocheDTO> getAllFiltered(ParametrosDTO parametrosDTO) {
        BooleanExpression booleanExpression = QCoche.coche.marca.id.eq(parametrosDTO.getMarcaId());
        OrderSpecifier<Integer> orderSpecifier = QCoche.coche.id.asc();
        return StreamSupport.stream(repository.findAll(booleanExpression, orderSpecifier).spliterator(), false)
                .map(mapper::toDTO).collect(Collectors.toList());
    }
}