package com.jambolao.bgfinancas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import com.jambolao.bgfinancas.model.categoria.Categoria;
import com.jambolao.bgfinancas.model.user.User;
import com.jambolao.bgfinancas.model.movimentacao.Movimentacao;
import com.jambolao.bgfinancas.model.movimentacao.MovimentacaoRequestDTO;
import com.jambolao.bgfinancas.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacoes")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService service;

    @GetMapping("/listarMovimentacao")
    public ResponseEntity<List<Movimentacao>> listCategorias() {
        List<Movimentacao> movimentacao = service.listMovimentacoes();
        return ResponseEntity.ok(movimentacao);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Movimentacao>> listByUserId(@PathVariable Long userId) {
        List<Movimentacao> movimentacoes = service.listByUserId(userId);
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimentacao> read(@PathVariable Long id) {
        Movimentacao movimentacao = service.readMovimentacao(id);
        return ResponseEntity.ok(movimentacao);
    }

    @PostMapping
    public ResponseEntity<Movimentacao> create(@RequestBody MovimentacaoRequestDTO movimentacao) {

        Movimentacao createdMovimentacao = service.createMovimentacao(movimentacao);
        return new ResponseEntity<Movimentacao>(createdMovimentacao, HttpStatus.CREATED);
    }

    // Buscar no banco de dados a categoria que tem o nome
    // Categoria categoria = repository.findByNome(movimentacao.categoria());

    @PutMapping("/{id}")
    public ResponseEntity<Movimentacao> update(@PathVariable Long id,
            @RequestBody MovimentacaoRequestDTO movimentacaoDTO) {
        // Buscar categoria pelo nome
        Optional<Categoria> categoriaOptional = service.findCategoriaByName(movimentacaoDTO.categoria());
        if (categoriaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setDescricao(movimentacaoDTO.descricao());
        movimentacao.setValor(movimentacaoDTO.valor());
        movimentacao.setTipo(movimentacaoDTO.tipo());
        movimentacao.setData(movimentacaoDTO.data());
        movimentacao.setCategoria(categoriaOptional.get());

        // Buscar usuário pelo ID
        Optional<User> userOptional = service.findUserById(movimentacaoDTO.user_id());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        movimentacao.setUserId(userOptional.get());

        // Atualizar movimentação
        Movimentacao updatedMovimentacao = service.updateMovimentacao(id, movimentacao);
        return ResponseEntity.ok(updatedMovimentacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteMovimentacao(id);
        return ResponseEntity.noContent().build();
    }

}
