package com.teknei.concesionario.dto;

import com.teknei.concesionario.model.Marca;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class ModeloDTO extends EntityModel<ModeloDTO> {

    private Integer id;
    private String nombre;
    private Marca marca = new Marca();
}