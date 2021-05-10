package com.teknei.concesionario.services;

import com.teknei.concesionario.common.GenericCrudService;
import com.teknei.concesionario.dto.MarcaDTO;
import com.teknei.concesionario.model.Marca;

import org.springframework.stereotype.Service;

@Service
public interface MarcaService extends GenericCrudService<Marca, MarcaDTO, Integer> {
}