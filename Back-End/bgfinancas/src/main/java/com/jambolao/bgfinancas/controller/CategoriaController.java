package com.jambolao.bgfinancas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jambolao.bgfinancas.model.categoria.Categoria;
import com.jambolao.bgfinancas.model.categoria.CategoriaRequestDTO;
import com.jambolao.bgfinancas.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> listCategorias() {
        List<Categoria> categoria = service.listCategorias();
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> read(@PathVariable Long id) {
        Categoria categoria = service.readCategoria(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> create(@RequestBody CategoriaRequestDTO categoriaRequest) {
        Categoria categoria = new Categoria(categoriaRequest);
        Categoria createdCategory = service.createCategoria(categoria);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequest) {
        Categoria categoria = new Categoria(categoriaRequest);
        Categoria updatedCategory = service.updateCategoria(id, categoria);
        return ResponseEntity.ok(updatedCategory);
    }                                                                                                                 

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }

}
