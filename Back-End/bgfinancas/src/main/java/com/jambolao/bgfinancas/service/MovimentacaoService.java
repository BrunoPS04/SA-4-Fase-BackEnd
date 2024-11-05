package com.jambolao.bgfinancas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jambolao.bgfinancas.model.movimentacao.Movimentacao;
import com.jambolao.bgfinancas.model.movimentacao.MovimentacaoRepository;
// import com.jambolao.bgfinancas.model.user.User;

@Service
public class MovimentacaoService {
    
    @Autowired
    private MovimentacaoRepository repository;

    @Transactional
    public Movimentacao createMovimentacao(Movimentacao movimentacao) {
        System.out.println("//////////////////////////////////////////////////////////////////////////////////");
        System.out.println(movimentacao.getDescricao());
        System.out.println(movimentacao.getValor());
        System.out.println(movimentacao.getTipo());
        System.out.println(movimentacao.getData());
        System.out.println(movimentacao.getCategoria());
        System.out.println("//////////////////////////////////////////////////////////////////////////////////");
        return repository.save(movimentacao);
    }

    @Transactional
    public List<Movimentacao> listMovimentacoes() {
        return(List<Movimentacao>) repository.findAll();
    }

    @Transactional
    public List<Movimentacao> listByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Transactional
    public Movimentacao updateMovimentacao(Long id, Movimentacao movimentacao) {
        movimentacao.setId(id);
        return repository.save(movimentacao);
    }

    @Transactional
    public boolean deleteMovimentacao(Long id) {
        Optional<Movimentacao> existingMovimentacao = repository.findById(id);
        if (existingMovimentacao.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Movimentacao readMovimentacao(Long id) {
        if (repository.existsById(id)) {
            return repository.findById(id).get();
        }
        return null;
    }

}
