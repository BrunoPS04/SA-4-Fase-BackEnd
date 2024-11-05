package com.jambolao.bgfinancas.model.movimentacao;

import java.time.LocalDate;
import com.jambolao.bgfinancas.model.categoria.Categoria;
import com.jambolao.bgfinancas.model.user.User;

public record MovimentacaoRequestDTO(String descricao, Float valor, String tipo, Categoria categoria, LocalDate data, User user_id) {
    
}
