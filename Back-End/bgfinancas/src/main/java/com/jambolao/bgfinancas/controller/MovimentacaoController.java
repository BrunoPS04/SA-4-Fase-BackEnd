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

import com.jambolao.bgfinancas.model.movimentacao.Movimentacao;
import com.jambolao.bgfinancas.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {
    
    @Autowired
    private MovimentacaoService service;

    @GetMapping
    public ResponseEntity<List<Movimentacao>> listCategorias() {
        List<Movimentacao> movimentacao = service.listMovimentacoes();
        return ResponseEntity.ok(movimentacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimentacao> read(Long id) {
        Movimentacao movimentacao = service.readMovimentacao(id);
        return ResponseEntity.ok(movimentacao);
    }

    @PostMapping
    public ResponseEntity<Movimentacao> create(@RequestBody Movimentacao movimentacao) {
        Movimentacao createdMovimentacao = service.createMovimentacao(movimentacao);
        return new ResponseEntity<Movimentacao> (createdMovimentacao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimentacao> update(@PathVariable Long id, @RequestBody Movimentacao movimentacao) {
        Movimentacao updatedMovimentacao = service.updateMovimentacao(id, movimentacao);
        return ResponseEntity.ok(updatedMovimentacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteMovimentacao(id);
        return ResponseEntity.noContent().build();
    }

}
