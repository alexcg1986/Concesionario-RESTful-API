package com.teknei.concesionario.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface GenericCrudService<Entity, DTO, Id extends Serializable> {

    List<DTO> getAll();

    DTO get(Id id);

    DTO save(DTO dto);

    void delete(Id id);
}