package br.com.leonardo.fechamentocompra;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class NovaCompraRequest {

    @Positive
    private int quantidade;
    @NotNull
    private Long idProduto;
    @NotNull
    private GatewayPagamento gateway;

    public NovaCompraRequest(int quantidade, Long idProduto, GatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }
}
