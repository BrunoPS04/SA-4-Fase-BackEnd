package com.jambolao.bgfinancas.model.user;

public record UserResponseDTO(Long id, String nome, String ultimoNome, String email, String senha) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getNome(), user.getUltimoNome(), user.getEmail(), user.getSenha());
    }
    
}