package com.teknei.concesionario.controller;

import java.util.List;

import com.teknei.concesionario.dto.DocumentoDTO;
import com.teknei.concesionario.service.FileNetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/filenet")
@CrossOrigin(origins = "http://localhost:8081")
public class FileNetRestController {

    @Autowired
    private FileNetService service;

    @GetMapping
    public ResponseEntity<List<DocumentoDTO>> getAll() {
        try {
            return new ResponseEntity<List<DocumentoDTO>>(service.getDocuments("alejandro", "Hola1234$"),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<DocumentoDTO>>(HttpStatus.BAD_REQUEST);
        }
    }
}