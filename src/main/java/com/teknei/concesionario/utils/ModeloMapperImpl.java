package com.teknei.concesionario.utils;

import com.teknei.concesionario.dto.ModeloDTO;
import com.teknei.concesionario.model.Modelo;

import org.springframework.stereotype.Component;

@Component
public class ModeloMapperImpl implements Mapper<Modelo, ModeloDTO> {

    @Override
    public Modelo toEntity(ModeloDTO dto) {
        Modelo modelo = new Modelo();
        modelo.setId(dto.getId());
        modelo.setNombre(dto.getNombre());
        return modelo;
    }

    @Override
    public ModeloDTO toDTO(Modelo entity) {
        ModeloDTO modeloDTO = new ModeloDTO();
        modeloDTO.setId(entity.getId());
        modeloDTO.setNombre(entity.getNombre());
        return modeloDTO;
    }
}