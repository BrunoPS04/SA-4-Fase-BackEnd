package com.jambolao.bgfinancas.model.user;

import java.util.List;

import com.jambolao.bgfinancas.model.movimentacao.Movimentacao;

public record UserResponseDTO(Long id, String nome, String ultimoNome, String email, String senha, List<Movimentacao> movimentacoes) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getNome(), user.getUltimoNome(), user.getEmail(), user.getSenha(), user.getMovimentacoes());
    }
    
}