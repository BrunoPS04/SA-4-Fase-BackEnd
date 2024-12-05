package com.jambolao.bgfinancas.model.movimentacao;

import java.time.LocalDate;

// import org.springframework.boot.context.properties.bind.DefaultValue;

import com.jambolao.bgfinancas.model.categoria.Categoria;
import com.jambolao.bgfinancas.model.user.User;

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
// import lombok.Builder.Default;

@Table(name = "movimentacoes")
@Entity
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
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    // @DefaultValue(value = "LocalDate.now()")
    // private LocalDate createdAt;

    public Movimentacao(String descricao, Float valor, String tipo, Categoria categoria, LocalDate data, User user_id) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.categoria = categoria;
        this.data = data;
        this.user_id = user_id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDate getData() {
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

    public User user_id() {
        return user_id;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setData(LocalDate data) {
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

    public void setUserId(User user_id) {
        this.user_id = user_id;
    }
    
}