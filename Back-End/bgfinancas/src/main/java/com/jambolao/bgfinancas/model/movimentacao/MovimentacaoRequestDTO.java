package com.jambolao.bgfinancas.model.movimentacao;

import java.time.LocalDate;

public record MovimentacaoRequestDTO(String descricao, Float valor, String tipo, String categoria, LocalDate data, Long user_id) {
    
}
