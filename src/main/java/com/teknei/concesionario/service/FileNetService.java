package com.teknei.concesionario.service;

import java.util.List;

import com.filenet.api.core.ObjectStore;

import org.springframework.stereotype.Service;

@Service
public interface FileNetService<CocheDTO> {

    void getObjectStore(); // this would return an objStore, void for testing

    void CreateDocument(List<CocheDTO> list, ObjectStore os);
}
