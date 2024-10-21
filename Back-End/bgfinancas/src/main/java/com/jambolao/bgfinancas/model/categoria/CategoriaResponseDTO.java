package com.jambolao.bgfinancas.model.categoria;

public record CategoriaResponseDTO (Long id, String nomeCategoria){

    public CategoriaResponseDTO(Categoria categoria){
        this(categoria.getId(), categoria.getNomeCategoria());
    }
    
}
