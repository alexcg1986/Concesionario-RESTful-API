package com.teknei.concesionario.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.teknei.concesionario.dto.CocheDTO;
import com.teknei.concesionario.dto.ParametrosDTO;
import com.teknei.concesionario.services.CocheService;

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
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:8081")
public class CocheRestController {

    @Autowired
    private CocheService service;

    @GetMapping
    public ResponseEntity<List<EntityModel<CocheDTO>>> getAll() {
        try {
            return new ResponseEntity<List<EntityModel<CocheDTO>>>(service.getAll().stream()
                    .map(item -> item
                            .add(WebMvcLinkBuilder.linkTo(CocheRestController.class).slash(item.getId()).withSelfRel()))
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<EntityModel<CocheDTO>>>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/filtrado")
    public ResponseEntity<List<EntityModel<CocheDTO>>> getAllFiltered(@RequestBody ParametrosDTO parametrosDTO) {
        try {
            return new ResponseEntity<List<EntityModel<CocheDTO>>>(service.getAllFiltered(parametrosDTO).stream()
                    .map(item -> item
                            .add(WebMvcLinkBuilder.linkTo(CocheRestController.class).slash(item.getId()).withSelfRel()))
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<EntityModel<CocheDTO>>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<CocheDTO> find(@PathVariable("id") Integer id) {
        try {
            CocheDTO item = service.get(id);
            if (item != null) {
                item.add(WebMvcLinkBuilder.linkTo(CocheRestController.class).slash(item.getId()).withSelfRel());
                return new ResponseEntity<CocheDTO>(item, HttpStatus.OK);
            } else
                return new ResponseEntity<CocheDTO>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<CocheDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<CocheDTO> save(@RequestBody CocheDTO dto) {
        try {
            CocheDTO savedItem = service.save(dto);
            savedItem.add(WebMvcLinkBuilder.linkTo(CocheRestController.class).slash(savedItem.getId()).withSelfRel());
            return new ResponseEntity<CocheDTO>(savedItem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<CocheDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CocheDTO> delete(@PathVariable("id") Integer id) {
        try {
            CocheDTO itemToDelete = service.get(id);
            if (itemToDelete != null) {
                service.delete(id);
                return new ResponseEntity<CocheDTO>(itemToDelete, HttpStatus.OK);
            } else {
                return new ResponseEntity<CocheDTO>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<CocheDTO>(HttpStatus.BAD_REQUEST);
        }
    }
}