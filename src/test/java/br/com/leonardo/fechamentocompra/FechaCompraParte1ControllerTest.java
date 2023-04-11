package br.com.leonardo.fechamentocompra;

import br.com.leonardo.cadastrocategorias.Categoria;
import br.com.leonardo.cadastroprodutos.NovaCaracteristicaRequest;
import br.com.leonardo.cadastroprodutos.Produto;
import br.com.leonardo.cadastrousuario.SenhaLimpa;
import br.com.leonardo.cadastrousuario.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

class FechaCompraParte1ControllerTest {

    @DisplayName("nao aceita abater estoque <= zero")
    @ParameterizedTest
    @CsvSource({ "0", "-1", "-100"})
    void teste1(int estoque) throws Exception {
        List<NovaCaracteristicaRequest> caracteristicas = List.of(
                new NovaCaracteristicaRequest("key", "value"),
                new NovaCaracteristicaRequest("key2", "value2"),
                new NovaCaracteristicaRequest("key3", "value3")
        );

        Categoria categoria = new Categoria("categoria");
        Usuario usuario = new Usuario("email@email.com.br", new SenhaLimpa("senhaaaa"));
        Produto produto = new Produto("nome", 10, "descricao", BigDecimal.TEN, categoria, usuario, caracteristicas);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           produto.abateEstoque(estoque);
        });

    }

    @DisplayName("verifica estoque do produto")
    @ParameterizedTest
    @CsvSource({"1,1,true", "1,2,false", "4,2,true", "1, 5, false"})
    void teste2(int estoque, int quantidadePedida, boolean resultadoEsperado) {
        List<NovaCaracteristicaRequest> caracteristicas = List.of(
                new NovaCaracteristicaRequest("key", "value"),
                new NovaCaracteristicaRequest("key2", "value2"),
                new NovaCaracteristicaRequest("key3", "value3")
        );

        Categoria categoria = new Categoria("categoria");
        Usuario usuario = new Usuario("email@email.com.br", new SenhaLimpa("senhaaaa"));
        Produto produto = new Produto("nome", estoque, "descricao", BigDecimal.TEN, categoria, usuario, caracteristicas);

        boolean resultado = produto.abateEstoque(quantidadePedida);
        Assertions.assertEquals(resultadoEsperado, resultado);
    }
}