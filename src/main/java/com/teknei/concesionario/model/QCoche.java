package com.teknei.concesionario.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCoche is a Querydsl query type for Coche
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCoche extends EntityPathBase<Coche> {

    private static final long serialVersionUID = 1597710056L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCoche coche = new QCoche("coche");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMarca marca;

    public final StringPath matricula = createString("matricula");

    public final QModelo modelo;

    public final QUsuario usuario;

    public QCoche(String variable) {
        this(Coche.class, forVariable(variable), INITS);
    }

    public QCoche(Path<? extends Coche> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCoche(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCoche(PathMetadata metadata, PathInits inits) {
        this(Coche.class, metadata, inits);
    }

    public QCoche(Class<? extends Coche> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.marca = inits.isInitialized("marca") ? new QMarca(forProperty("marca")) : null;
        this.modelo = inits.isInitialized("modelo") ? new QModelo(forProperty("modelo")) : null;
        this.usuario = inits.isInitialized("usuario") ? new QUsuario(forProperty("usuario"), inits.get("usuario")) : null;
    }

}

