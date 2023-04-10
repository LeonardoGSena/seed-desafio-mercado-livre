package br.com.leonardo.detalheproduto;

import br.com.leonardo.adicionapergunta.Pergunta;
import br.com.leonardo.cadastroprodutos.ImagemProduto;
import br.com.leonardo.cadastroprodutos.Opinioes;
import br.com.leonardo.cadastroprodutos.Produto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class DetalheProdutoView {

    private String descricao;
    private String nome;
    private BigDecimal preco;
    //1
    private Set<DetalheProdutoCaracteristica> caracteristicas;
    private Set<String> linksImagens;
    private Set<String> perguntas;
    private Set<Map<String, String>> opinioes;
    private double mediaDasNotas;
    private int totalOpinioes;

    public DetalheProdutoView(Produto produto) {
        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        //1
        this.caracteristicas = produto.mapeiaCaracteristicas(DetalheProdutoCaracteristica::new);
        //1
        this.linksImagens = produto.mapeiaImagens(ImagemProduto::getLink);
        //1
        this.perguntas = produto.mapeiaPerguntas(Pergunta::getTitulo);
        //1
        Opinioes opinioes = produto.getOpinioes();
        //1
        this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
        });

        this.mediaDasNotas = opinioes.media();
        this.totalOpinioes = opinioes.totalOpinioes();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public Set<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public double getMediaDasNotas() {
        return mediaDasNotas;
    }

    public int getTotalOpinioes() {
        return totalOpinioes;
    }
}
