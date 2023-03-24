package br.com.leonardo.cadastroprodutos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

class NovoProdutoRequestTest {

    @ParameterizedTest
    @MethodSource("gerador")
    @DisplayName("cria produto com diversos tipos de caracteristicas")
    void teste1(int esperado, List<NovaCaracteristicaRequest> caracteristicas) throws Exception {
        NovoProdutoRequest request = new NovoProdutoRequest("nome", 10, "descricao", BigDecimal.TEN, 1L, caracteristicas);

        Assertions.assertEquals(esperado, request.buscaCaracteristicasIguais().size());
    }

    private static Stream<Arguments> gerador() {
       return Stream.of(
                Arguments.of(0, List.of()),
                        Arguments.of(0, List.of(new NovaCaracteristicaRequest("key", "value"))),
                        Arguments.of(0, List.of(new NovaCaracteristicaRequest("key", "value"), new NovaCaracteristicaRequest("key1", "value1"))),
                        Arguments.of(1, List.of(new NovaCaracteristicaRequest("key", "value"), new NovaCaracteristicaRequest("key", "value")))
        );
    }
}