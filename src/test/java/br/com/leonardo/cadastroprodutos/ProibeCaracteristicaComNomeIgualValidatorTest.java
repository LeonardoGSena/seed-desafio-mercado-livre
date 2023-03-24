package br.com.leonardo.cadastroprodutos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

class ProibeCaracteristicaComNomeIgualValidatorTest {

    @ParameterizedTest
    @MethodSource("gerador")
    @DisplayName("não aceita produto com caracterista igual")
    void teste1(boolean resultadoEsperado, List<NovaCaracteristicaRequest> caracateristicas) throws Exception {
        NovoProdutoRequest request = new NovoProdutoRequest("nome", 10, "descricao", BigDecimal.TEN, 1L, caracateristicas);
        ProibeCaracteristicaComNomeIgualValidator validador = new ProibeCaracteristicaComNomeIgualValidator();
        Errors errors = new BeanPropertyBindingResult(request, "teste");

        validador.validate(request, errors);
        Assertions.assertEquals(resultadoEsperado, errors.hasFieldErrors("caracteristicas"));
    }

    private static Stream<Arguments> gerador() {
        return Stream.of(
                Arguments.of(false, List.of(new NovaCaracteristicaRequest("key", "value"))),
                Arguments.of(true, List.of(new NovaCaracteristicaRequest("key", "value"), new NovaCaracteristicaRequest("key", "value"))));
    }
}