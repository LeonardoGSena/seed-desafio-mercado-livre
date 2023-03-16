package br.com.leonardo.cadastrousuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Optional;
import java.util.stream.Stream;

class ProibeUsuarioComEmailDuplicadoValidatorTest {

    @DisplayName("deveria lidar com email duplicado")
    @ParameterizedTest
    @MethodSource("geradorTeste1")
    void teste1(Optional<Usuario> possivelUsuario, boolean esperado) throws Exception {
        UsuarioRepository usuarioRepositorio = Mockito.mock(UsuarioRepository.class);
        ProibeUsuarioComEmailDuplicadoValidator validador = new ProibeUsuarioComEmailDuplicadoValidator(usuarioRepositorio);
        NovoUsuarioRequest target = new NovoUsuarioRequest("email@email.com.br", "senhaa");
        Errors erros = new BeanPropertyBindingResult(target, "teste");
        Mockito.when(usuarioRepositorio.findByEmail("email@email.com.br")).thenReturn(possivelUsuario);
        validador.validate(target, erros);

        Assertions.assertEquals(esperado, erros.hasFieldErrors("email"));
    }

    private static Stream<Arguments> geradorTeste1() {
        Optional<Usuario> usuario = Optional.of(new Usuario("email@email.com.br", new SenhaLimpa("senhaa")));
        return Stream.of(Arguments.of(usuario, true), Arguments.of(Optional.empty(), false));
    }
}