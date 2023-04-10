package br.com.leonardo.detalheproduto;

import br.com.leonardo.cadastroprodutos.CaracteristicaProduto;

public class DetalheProdutoCaracteristica {

    private final String nome;
    private final String descricao;

    public DetalheProdutoCaracteristica(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
