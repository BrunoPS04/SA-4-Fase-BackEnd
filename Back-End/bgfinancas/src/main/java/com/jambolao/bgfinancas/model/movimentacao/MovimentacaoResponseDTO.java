package com.jambolao.bgfinancas.model.movimentacao;

import java.time.LocalDate;
import com.jambolao.bgfinancas.model.categoria.Categoria;
import com.jambolao.bgfinancas.model.user.User;

public record MovimentacaoResponseDTO(Long id, String descricao, Float valor, String tipo, Categoria categoria,
        LocalDate data, User user_id) {

    public MovimentacaoResponseDTO(Movimentacao movimentacao) {
        this(movimentacao.getId(), movimentacao.getDescricao(), movimentacao.getValor(), movimentacao.getTipo(),
                movimentacao.getCategoria(), movimentacao.getData(), movimentacao.getUser_id());
    }

}
