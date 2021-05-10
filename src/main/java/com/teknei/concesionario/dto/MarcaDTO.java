package com.teknei.concesionario.dto;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class MarcaDTO extends EntityModel<MarcaDTO> {

    private Integer id;
    private String nombre;
}