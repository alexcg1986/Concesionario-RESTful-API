package com.teknei.concesionario.utils;

import com.teknei.concesionario.dto.CocheDTO;
import com.teknei.concesionario.model.Coche;

import org.springframework.stereotype.Component;

@Component
public class CocheMapperImpl implements Mapper<Coche, CocheDTO> {

    @Override
    public Coche toEntity(CocheDTO dto) {
        Coche coche = new Coche();
        coche.setId(dto.getId());
        coche.getMarca().setId(dto.getMarca().getId());
        coche.getModelo().setId(dto.getModelo().getId());
        coche.setMatricula(dto.getMatricula());
        coche.getUsuario().setId(dto.getUsuario().getId());
        return coche;
    }

    @Override
    public CocheDTO toDTO(Coche entity) {
        CocheDTO cocheDTO = new CocheDTO();
        cocheDTO.setId(entity.getId());
        cocheDTO.getMarca().setId(entity.getMarca().getId());
        cocheDTO.getMarca().setNombre(entity.getMarca().getNombre());
        cocheDTO.getModelo().setId(entity.getModelo().getId());
        cocheDTO.getModelo().setNombre(entity.getModelo().getNombre());
        cocheDTO.setMatricula(entity.getMatricula());
        cocheDTO.getUsuario().setId(entity.getUsuario().getId());
        cocheDTO.getUsuario().setEmail(entity.getUsuario().getEmail());
        cocheDTO.getUsuario().setPassword(entity.getUsuario().getPassword());
        return cocheDTO;
    }
}