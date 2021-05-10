package com.teknei.concesionario.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QModelo is a Querydsl query type for Modelo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QModelo extends EntityPathBase<Modelo> {

    private static final long serialVersionUID = -1724277070L;

    public static final QModelo modelo = new QModelo("modelo");

    public final ListPath<Coche, QCoche> coches = this.<Coche, QCoche>createList("coches", Coche.class, QCoche.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nombre = createString("nombre");

    public QModelo(String variable) {
        super(Modelo.class, forVariable(variable));
    }

    public QModelo(Path<? extends Modelo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QModelo(PathMetadata metadata) {
        super(Modelo.class, metadata);
    }

}

