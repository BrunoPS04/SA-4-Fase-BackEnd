package com.jambolao.bgfinancas.model.movimentacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    @Query(value = "SELECT * FROM movimentacoes WHERE user_id = :userId", nativeQuery = true)
    List<Movimentacao> findByUserId(@Param("userId") Long user_id);

}
