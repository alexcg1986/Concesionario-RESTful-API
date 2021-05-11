package com.teknei.concesionario.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.teknei.concesionario.dto.MarcaDTO;
import com.teknei.concesionario.services.MarcaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/marca")
@CrossOrigin(origins = "http://localhost:8081")
public class MarcaRestController {

    @Autowired
    private MarcaService service;

    @GetMapping
    public ResponseEntity<List<EntityModel<MarcaDTO>>> getAll() {
        try {
            return new ResponseEntity<List<EntityModel<MarcaDTO>>>(service.getAll().stream()
                    .map(item -> item
                            .add(WebMvcLinkBuilder.linkTo(MarcaRestController.class).slash(item.getId()).withSelfRel()))
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<EntityModel<MarcaDTO>>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<MarcaDTO> find(@PathVariable("id") Integer id) {
        try {
            MarcaDTO item = service.get(id);
            if (item != null) {
                item.add(WebMvcLinkBuilder.linkTo(MarcaRestController.class).slash(item.getId()).withSelfRel());
                return new ResponseEntity<MarcaDTO>(item, HttpStatus.OK);
            } else
                return new ResponseEntity<MarcaDTO>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<MarcaDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> save(@RequestBody MarcaDTO dto) {
        try {
            MarcaDTO savedItem = service.save(dto);
            savedItem.add(WebMvcLinkBuilder.linkTo(MarcaRestController.class).slash(savedItem.getId()).withSelfRel());
            return new ResponseEntity<MarcaDTO>(savedItem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<MarcaDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MarcaDTO> delete(@PathVariable("id") Integer id) {
        try {
            MarcaDTO itemToDelete = service.get(id);
            if (itemToDelete != null) {
                service.delete(id);
                return new ResponseEntity<MarcaDTO>(itemToDelete, HttpStatus.OK);
            } else {
                return new ResponseEntity<MarcaDTO>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<MarcaDTO>(HttpStatus.BAD_REQUEST);
        }
    }
}