package com.teknei.concesionario.common;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.teknei.concesionario.util.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericCrudServiceImpl<Entity, DTO, Id extends Serializable>
        implements GenericCrudService<Entity, DTO, Id> {

    @Autowired
    Mapper<Entity, DTO> mapper;

    @Override
    public List<DTO> getAll() {
        return getRepository().findAll(Sort.by(Sort.Direction.ASC, "id")).stream().map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DTO get(Id id) {
        Optional<Entity> optional = getRepository().findById(id);
        return optional.isPresent() ? mapper.toDTO(optional.get()) : null;
    }

    @Override
    public DTO save(DTO dto) {
        return mapper.toDTO(getRepository().save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Id id) {
        getRepository().deleteById(id);
    }

    public abstract JpaRepository<Entity, Id> getRepository();
}