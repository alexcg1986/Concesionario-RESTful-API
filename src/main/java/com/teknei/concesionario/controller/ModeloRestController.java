package com.teknei.concesionario.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.teknei.concesionario.dto.ModeloDTO;
import com.teknei.concesionario.dto.ParametrosDTO;
import com.teknei.concesionario.service.ModeloService;

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
@RequestMapping("/api/modelo")
@CrossOrigin(origins = "http://localhost:8081")
public class ModeloRestController {

    @Autowired
    private ModeloService service;

    @GetMapping
    public ResponseEntity<List<EntityModel<ModeloDTO>>> getAll() {
        try {
            return new ResponseEntity<List<EntityModel<ModeloDTO>>>(service.getAll().stream()
                    .map(item -> item.add(
                            WebMvcLinkBuilder.linkTo(ModeloRestController.class).slash(item.getId()).withSelfRel()))
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<EntityModel<ModeloDTO>>>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/filtrado")
    public ResponseEntity<List<EntityModel<ModeloDTO>>> getAllFiltered(@RequestBody ParametrosDTO parametrosDTO) {
        try {
            return new ResponseEntity<List<EntityModel<ModeloDTO>>>(service.getAllFiltered(parametrosDTO).stream()
                    .map(item -> item
                            .add(WebMvcLinkBuilder.linkTo(CocheRestController.class).slash(item.getId()).withSelfRel()))
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<EntityModel<ModeloDTO>>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ModeloDTO> find(@PathVariable("id") Integer id) {
        try {
            ModeloDTO item = service.get(id);
            if (item != null) {
                item.add(WebMvcLinkBuilder.linkTo(ModeloRestController.class).slash(item.getId()).withSelfRel());
                return new ResponseEntity<ModeloDTO>(item, HttpStatus.OK);
            } else
                return new ResponseEntity<ModeloDTO>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<ModeloDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ModeloDTO> save(@RequestBody ModeloDTO dto) {
        try {
            ModeloDTO savedItem = service.save(dto);
            savedItem.add(WebMvcLinkBuilder.linkTo(ModeloRestController.class).slash(savedItem.getId()).withSelfRel());
            return new ResponseEntity<ModeloDTO>(savedItem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ModeloDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ModeloDTO> delete(@PathVariable("id") Integer id) {
        try {
            ModeloDTO itemToDelete = service.get(id);
            if (itemToDelete != null) {
                service.delete(id);
                return new ResponseEntity<ModeloDTO>(itemToDelete, HttpStatus.OK);
            } else {
                return new ResponseEntity<ModeloDTO>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<ModeloDTO>(HttpStatus.BAD_REQUEST);
        }
    }
}