package br.com.leonardo.adicionapergunta;

import br.com.leonardo.cadastroprodutos.Produto;
import br.com.leonardo.cadastrousuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    public String getTitulo() {
        return titulo;
    }

    public Usuario getInteressado() {
        return this.interessada;
    }

    public Usuario getDonoProduto() {
        return produto.getDono();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pergunta pergunta = (Pergunta) o;

        if (titulo != null ? !titulo.equals(pergunta.titulo) : pergunta.titulo != null) return false;
        if (interessada != null ? !interessada.equals(pergunta.interessada) : pergunta.interessada != null)
            return false;
        return produto != null ? produto.equals(pergunta.produto) : pergunta.produto == null;
    }

    @Override
    public int hashCode() {
        int result = titulo != null ? titulo.hashCode() : 0;
        result = 31 * result + (interessada != null ? interessada.hashCode() : 0);
        result = 31 * result + (produto != null ? produto.hashCode() : 0);
        return result;
    }


}
