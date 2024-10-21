package com.jambolao.bgfinancas.model.movimentacao;

import java.time.LocalDateTime;
import com.jambolao.bgfinancas.model.categoria.Categoria;

public record MovimentacaoRequestDTO(String descricao, Float valor, String tipo, Categoria categoria, LocalDateTime data) {
    
}
