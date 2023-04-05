package br.com.leonardo.adicionapergunta;

import br.com.leonardo.cadastroprodutos.Produto;
import br.com.leonardo.cadastrousuario.NovoUsuarioRequest;
import br.com.leonardo.cadastrousuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario interessada;
    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;
    private LocalDate localDate;

    private Pergunta() {
    }

    public Pergunta(@NotBlank String titulo, @NotNull @Valid Usuario interessada, @NotNull @Valid Produto produto) {
        this.titulo = titulo;
        this.interessada = interessada;
        this.produto = produto;
        localDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", interessada=" + interessada +
                ", produto=" + produto +
                '}';
    }

    public Usuario getInteressado() {
        return this.interessada;
    }

    public Usuario getDonoProduto() {
        return produto.getDono();
    }
}
