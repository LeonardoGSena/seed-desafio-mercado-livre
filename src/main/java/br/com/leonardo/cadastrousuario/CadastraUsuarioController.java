package br.com.leonardo.cadastrousuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CadastraUsuarioController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private ProibeUsuarioComEmailDuplicadoValidator proibeUsuarioComEmailDuplicadoValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(proibeUsuarioComEmailDuplicadoValidator);
    }

    @PostMapping(value = "/usuarios")
    @Transactional
    public String cria(@RequestBody  @Valid NovoUsuarioRequest request) {
        Usuario novoUsuario = request.toUsuario();
        manager.persist(novoUsuario);
        return novoUsuario.toString();
    }
}
