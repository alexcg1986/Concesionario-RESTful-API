package com.teknei.concesionario.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "modelo")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "nombre", nullable = false, length = 45, unique = true)
    private String nombre;
    @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coche> coches = new ArrayList<>();
}