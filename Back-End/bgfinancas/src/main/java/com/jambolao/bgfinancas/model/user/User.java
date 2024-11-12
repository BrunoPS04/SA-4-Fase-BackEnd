package com.jambolao.bgfinancas.model.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jambolao.bgfinancas.model.movimentacao.Movimentacao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;
    @Column(nullable = false, length = 60)
    private String ultimoNome;
    @Column(nullable = false, length = 60)
    private String email;
    @Column(nullable = false, length = 60)
    private String senha;
    @OneToMany(mappedBy = "user_id")
    @JsonIgnore
    private List<Movimentacao> movimentacoes;

    public User(UserRequestDTO data) {
        this.nome = data.nome();
        this.ultimoNome = data.ultimoNome();
        this.email = data.email();
        this.senha = data.senha();
        this.movimentacoes = data.movimentacoes();
    }

    public String getNome(){
        return this.nome;
    }

    public String getUltimoNome(){
        return this.ultimoNome;
    }

    public String getEmail(){
        return this.email;
    }

    public String getSenha(){
        return this.senha;
    }

    public List<Movimentacao> getMovimentacoes() {
        return this.movimentacoes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public void setMovimentaoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public User get() {
        return this;
    }

}
