package br.com.leonardo.cadastrousuario;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeUsuarioComEmailDuplicadoValidator implements Validator {


    private final UsuarioRepository usuarioRepository;

    public ProibeUsuarioComEmailDuplicadoValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoUsuarioRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        NovoUsuarioRequest request = (NovoUsuarioRequest) target;
        Optional<Usuario> possivelUsuario = usuarioRepository.findByEmail(request.getEmail());
        if (possivelUsuario.isPresent()) {
            errors.rejectValue("email", null, "ja existe esse email no sistema");
        }
    }
}
