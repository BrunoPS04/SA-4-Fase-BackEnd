package com.jambolao.bgfinancas.model.categoria;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class CategoriaDeserializer extends JsonDeserializer<Categoria> {

    @Override
    public Categoria deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        // Lê o valor string do JSON (por exemplo, "Transporte")
        String nomeCategoria = jsonParser.getText();
        
        // Retorna uma nova instância de Categoria com o nomeCategoria definido
        return new Categoria(nomeCategoria);
    }
}
