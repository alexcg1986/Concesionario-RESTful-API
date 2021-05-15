package com.teknei.concesionario.service;

import java.util.List;

import com.teknei.concesionario.common.GenericCrudService;
import com.teknei.concesionario.dto.ModeloDTO;
import com.teknei.concesionario.dto.ParametrosDTO;
import com.teknei.concesionario.model.Modelo;

import org.springframework.stereotype.Service;

@Service
public interface ModeloService extends GenericCrudService<Modelo, ModeloDTO, Integer> {

    List<ModeloDTO> getAllFiltered(ParametrosDTO parametrosDTO);
}