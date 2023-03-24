package br.com.leonardo.cadastroprodutos;

import br.com.leonardo.cadastrocategorias.Categoria;
import br.com.leonardo.cadastrousuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @Positive
    private int quantidade;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Valid
    @ManyToOne
    private Categoria categoria;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario dono;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<CaracteristicaProduto>();

    private Produto() {
    }

    public Produto(@NotBlank String nome,
                   @Positive int quantidade,
                   @NotBlank @Length String descricao,
                   @NotNull @Positive BigDecimal valor,
                   @NotNull @Valid Categoria categoria,
                   @NotNull @Valid Usuario dono, @Size(min = 3) @Valid Collection<NovaCaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.dono = dono;
        Set<CaracteristicaProduto> novasCaracteristicas = caracteristicas
                .stream().map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet());
        this.caracteristicas.addAll(novasCaracteristicas);
        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 caracteristicas");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        return nome != null ? nome.equals(produto.nome) : produto.nome == null;
    }

    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", categoria=" + categoria +
                ", dono=" + dono +
                ", caracteristicas=" + caracteristicas +
                '}';
    }
}
