package br.com.leonardo.cadastroprodutos;

import br.com.leonardo.cadastrocategorias.Categoria;
import br.com.leonardo.cadastrousuario.Usuario;
import br.com.leonardo.compartilhado.ExistsId;
import br.com.leonardo.compartilhado.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NovoProdutoRequest {

    @NotBlank
    @UniqueValue(domainClass = Produto.class, fieldName = "nome")
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
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;
    @Size(min = 3)
    @Valid
    private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();

    public NovoProdutoRequest(@NotBlank String nome,
                              @Positive int quantidade,
                              @NotBlank @Length(max = 1000) String descricao,
                              @NotNull @Positive BigDecimal valor,
                              @NotNull Long idCategoria, List<NovaCaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public List<NovaCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto toModel(EntityManager manager, Usuario dono) {
        Categoria categoria = manager.find(Categoria.class, idCategoria);
        return new Produto(nome, quantidade, descricao, valor, categoria, dono, caracteristicas);
    }

    @Override
    public String toString() {
        return "NovoProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", idCategoria=" + idCategoria +
                ", caracteristicas=" + caracteristicas +
                '}';
    }

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomeIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();
        for (NovaCaracteristicaRequest caracteristica : caracteristicas) {
            String nome = caracteristica.getNome();
            if (!nomeIguais.add(nome)) {
                resultados.add(nome);
            }
        }
        return resultados;
    }
}
