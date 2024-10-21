package com.jambolao.bgfinancas.model.movimentacao;

import java.time.LocalDateTime;
import com.jambolao.bgfinancas.model.categoria.Categoria;

public record MovimentacaoResponseDTO(Long id, String descricao, Float valor, String tipo, Categoria categoria, LocalDateTime data) {

    public MovimentacaoResponseDTO(Movimentacao movimentacao) {
        this(movimentacao.getId(), movimentacao.getDescricao(), movimentacao.getValor(), movimentacao.getTipo(), movimentacao.getCategoria(), movimentacao.getData());
    }
    
}
