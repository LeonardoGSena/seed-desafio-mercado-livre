package br.com.leonardo.adicionapergunta;

import br.com.leonardo.cadastroprodutos.Produto;
import br.com.leonardo.cadastrousuario.Usuario;
import br.com.leonardo.cadastrousuario.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NovaPerguntaControler {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private Emails emails;

    private final UsuarioRepository usuarioRepository;

    public NovaPerguntaControler(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping(value = "/produtos/{id}/perguntas")
    @Transactional
    public String cria(@RequestBody @Valid NovaPerguntaRequest request, @PathVariable("id") Long id) {
        Produto produto = manager.find(Produto.class, id);
        Usuario interessada = usuarioRepository.findByEmail("seuemail@deveficiente.com.br").get();
        Pergunta novaPergunta = request.toModel(interessada, produto);
        manager.persist(novaPergunta);

        emails.novaPergunta(novaPergunta);
        return novaPergunta.toString();
    }
}
