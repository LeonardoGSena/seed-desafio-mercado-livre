package br.com.leonardo.adicionapergunta;

import br.com.leonardo.cadastroprodutos.Produto;
import br.com.leonardo.cadastrousuario.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    @JsonCreator
    public NovaPerguntaRequest(@JsonProperty("titulo") String titulo) {
        this.titulo = titulo;
    }


    @Override
    public String toString() {
        return "NovaPerguntaRequest{" +
                "titulo='" + titulo + '\'' +
                '}';
    }

    public Pergunta toModel(@NotNull @Valid Usuario interessada, @NotNull @Valid Produto produto) {
        return new Pergunta(titulo, interessada, produto);
    }
}
