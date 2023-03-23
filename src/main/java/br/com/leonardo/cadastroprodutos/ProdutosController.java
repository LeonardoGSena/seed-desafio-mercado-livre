package br.com.leonardo.cadastroprodutos;

import br.com.leonardo.cadastrousuario.Usuario;
import br.com.leonardo.cadastrousuario.UsuarioRepository;
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
public class ProdutosController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    }

    @PostMapping(value = "/produtos")
    @Transactional
    public String cria(@RequestBody @Valid NovoProdutoRequest request) {
        Usuario dono = usuarioRepository.findByEmail("leonardo@deveficiente.com.br").get();
        Produto produto = request.toModel(manager, dono);
        return produto.toString();
    }
}
