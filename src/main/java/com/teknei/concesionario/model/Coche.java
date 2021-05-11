package com.teknei.concesionario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "coche")
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Marca marca = new Marca();
    @ManyToOne(fetch = FetchType.LAZY)
    private Modelo modelo = new Modelo();
    @Column(name = "matricula", nullable = false, length = 8, unique = true)
    private String matricula;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario = new Usuario();
}