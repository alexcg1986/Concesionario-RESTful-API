package com.teknei.concesionario.service;

import java.util.List;

import com.filenet.api.core.ObjectStore;

import org.springframework.stereotype.Service;

@Service
public interface FileNetService<Coche> {

    ObjectStore getObjectStore();

    void CreateDocument(List<Coche> list, ObjectStore os);
}
