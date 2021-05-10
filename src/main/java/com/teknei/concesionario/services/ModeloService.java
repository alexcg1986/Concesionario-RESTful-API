package com.teknei.concesionario.services;

import com.teknei.concesionario.common.GenericCrudService;
import com.teknei.concesionario.dto.ModeloDTO;
import com.teknei.concesionario.model.Modelo;

import org.springframework.stereotype.Service;

@Service
public interface ModeloService extends GenericCrudService<Modelo, ModeloDTO, Integer> {
}