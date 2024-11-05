package com.jambolao.bgfinancas.model.user;

import java.util.List;

import com.jambolao.bgfinancas.model.movimentacao.Movimentacao;

public record UserRequestDTO(String nome, String ultimoNome, String email, String senha, List<Movimentacao> movimentacoes){
}
