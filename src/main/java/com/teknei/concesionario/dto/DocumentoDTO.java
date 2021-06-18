package com.teknei.concesionario.dto;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class DocumentoDTO extends EntityModel<DocumentoDTO> {

    private String guid;
    private String titulo;
    private List<String> marcas;
    private List<String> modelos;
    private List<String> matriculas;
}