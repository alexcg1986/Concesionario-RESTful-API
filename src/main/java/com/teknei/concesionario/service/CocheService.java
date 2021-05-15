package com.teknei.concesionario.service;

import java.util.List;

import com.teknei.concesionario.common.GenericCrudService;
import com.teknei.concesionario.dto.CocheDTO;
import com.teknei.concesionario.dto.ParametrosDTO;
import com.teknei.concesionario.model.Coche;

import org.springframework.stereotype.Service;

@Service
public interface CocheService extends GenericCrudService<Coche, CocheDTO, Integer> {

    List<CocheDTO> getAllFiltered(ParametrosDTO parametrosDTO);
}