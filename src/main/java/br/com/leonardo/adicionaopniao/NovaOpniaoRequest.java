package br.com.leonardo.adicionaopniao;

import br.com.leonardo.cadastroprodutos.Produto;
import br.com.leonardo.cadastrousuario.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class NovaOpniaoRequest {

    @Min(1)
    @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;

    public NovaOpniaoRequest(@Max(1) @Min(5) int nota, @NotBlank String titulo, @NotBlank @Size(max = 5) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario consumidor) {
        return new Opiniao(nota, titulo, descricao, produto, consumidor);
    }
}
