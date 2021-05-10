package com.teknei.concesionario.dto;

import com.teknei.concesionario.model.Marca;
import com.teknei.concesionario.model.Modelo;
import com.teknei.concesionario.model.Usuario;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class CocheDTO extends EntityModel<CocheDTO> {

    private Integer id;
    private Marca marca = new Marca();
    private Modelo modelo = new Modelo();
    private String matricula;
    private Usuario usuario = new Usuario();
}