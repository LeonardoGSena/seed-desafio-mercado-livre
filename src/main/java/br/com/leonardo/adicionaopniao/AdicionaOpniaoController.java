package br.com.leonardo.adicionaopniao;

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
public class AdicionaOpniaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PersistenceContext
    //1
    private EntityManager manager;

    @PostMapping(value = "/produtos/{id}/opiniao")
    @Transactional
    //1
    public String adiciona(@RequestBody @Valid NovaOpniaoRequest request, @PathVariable("id") Long id) {
        //1
        Produto produto = manager.find(Produto.class, id);
        //1
        Usuario consumidor = usuarioRepository.findByEmail("leonardo@deveficiente.com.br").get();
        //1
        Opiniao novaOpniao = request.toModel(produto, consumidor);
        manager.persist(novaOpniao);

        return novaOpniao.toString();
    }
}
