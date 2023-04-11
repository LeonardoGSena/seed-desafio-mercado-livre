package br.com.leonardo.fechamentocompra;

import br.com.leonardo.cadastroprodutos.Produto;
import br.com.leonardo.cadastrousuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @Valid
    private Produto produtoASerComprado;
    @Positive
    private int quantidade;
    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;
    @Enumerated
    @NotNull
    private GatewayPagamento gateway;


    public Compra(@NotNull @Valid Produto produtoASerComprado, @Positive int quantidade, @NotNull @Valid Usuario comprador, GatewayPagamento gateway) {
        this.produtoASerComprado = produtoASerComprado;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gateway = gateway;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtoASerComprado=" + produtoASerComprado +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", gateway=" + gateway +
                '}';
    }

    public Long getId() {
        return id;
    }
}
