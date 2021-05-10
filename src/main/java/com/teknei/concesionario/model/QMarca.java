package com.teknei.concesionario.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMarca is a Querydsl query type for Marca
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMarca extends EntityPathBase<Marca> {

    private static final long serialVersionUID = 1606542448L;

    public static final QMarca marca = new QMarca("marca");

    public final ListPath<Coche, QCoche> coches = this.<Coche, QCoche>createList("coches", Coche.class, QCoche.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nombre = createString("nombre");

    public QMarca(String variable) {
        super(Marca.class, forVariable(variable));
    }

    public QMarca(Path<? extends Marca> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMarca(PathMetadata metadata) {
        super(Marca.class, metadata);
    }

}

