package com.teknei.concesionario.service;

import java.util.Iterator;
import java.util.List;

import com.filenet.api.core.Connection;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;
import com.teknei.concesionario.dto.CocheDTO;
import com.teknei.concesionario.dto.DocumentoDTO;

import org.springframework.stereotype.Service;

@Service
public interface FileNetService {

    Connection getConnection();

    void pushSubject(final Connection connection, final String USERNAME, final String PASS);

    Iterator<?> getObjectStoreIterator(final Connection connection);

    Folder getFolder(final ObjectStore objectStore);

    void popSubject();

    void generatePDF(final List<CocheDTO> list, final String USERNAME, final String PASS);

    List<DocumentoDTO> getDocuments(final String USERNAME, final String PASS);
}
