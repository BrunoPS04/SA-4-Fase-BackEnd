package com.jambolao.bgfinancas.model.movimentacao;

import java.time.LocalDateTime;

import com.jambolao.bgfinancas.model.categoria.Categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "movimentacoes")
@Entity(name = "movimentacao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,  length = 100)
    private String descricao;
    @Column(nullable = false)
    private Float valor;
    @Column(nullable = false,  length = 100)
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    @Column(nullable = false)
    private LocalDateTime data;

    public Movimentacao(MovimentacaoRequestDTO data) {
        this.descricao = data.descricao();
        this.valor = data.valor();
        this.tipo = data.tipo();
        this.categoria = data.categoria();
        this.data = data.data();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId() {
        return id;
    }
    
    public String getTipo() {
        return tipo;
    }

    public Float getValor() {
        return valor;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
    
}