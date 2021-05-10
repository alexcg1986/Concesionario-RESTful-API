package com.teknei.concesionario.utils;

import com.teknei.concesionario.dto.MarcaDTO;
import com.teknei.concesionario.model.Marca;

import org.springframework.stereotype.Component;

@Component
public class MarcaMapperImpl implements Mapper<Marca, MarcaDTO> {

    @Override
    public Marca toEntity(MarcaDTO dto) {
        Marca marca = new Marca();
        marca.setId(dto.getId());
        marca.setNombre(dto.getNombre());
        return marca;
    }

    @Override
    public MarcaDTO toDTO(Marca entity) {
        MarcaDTO marcaDTO = new MarcaDTO();
        marcaDTO.setId(entity.getId());
        marcaDTO.setNombre(entity.getNombre());
        return marcaDTO;
    }
}