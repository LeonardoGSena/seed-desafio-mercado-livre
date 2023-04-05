package br.com.leonardo.cadastroprodutos;

import br.com.leonardo.cadastrousuario.Usuario;
import br.com.leonardo.cadastrousuario.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
public class ProdutosController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    //1
    private UsuarioRepository usuarioRepository;
    @Autowired
    //1
    private UploaderFake uploaderFake;

    @InitBinder(value = "novoProdutoRequest")
    public void init(WebDataBinder webDataBinder) {
        //1
        webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    }

    @PostMapping(value = "/produtos")
    @Transactional
    //1
    public String cria(@RequestBody @Valid NovoProdutoRequest request) {
        //1
        Usuario dono = usuarioRepository.findByEmail("leonardo@deveficiente.com.br").get();
        //1
        Produto produto = request.toModel(manager, dono);
        manager.persist(produto);
        return produto.toString();
    }

    @PostMapping(value = "/produtos/{id}/imagens")
    @Transactional
    //1
    public String adicionaImagens(@PathVariable("id") Long id,  @Valid NovasImagensRequest request) {
        /**
         * 1- enviar imagens para o local onde elas irao ficar
         * 2- pegar os links de todas as imagens
         * 3- associar esses links com os produtos em questao
         * 4- preciso carregar o produto
         * 5- depois de associar, eu preciso atualizar a nova versao do produto
         */

        Usuario dono = usuarioRepository.findByEmail("leonardo@deveficiente.com.br").get();
        Produto produto = manager.find(Produto.class, id);

        if (!produto.pertenceAoUsuario(dono)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploaderFake.envia(request.getImagens());
        produto.associaImagens(links);

        manager.merge(produto);
        return produto.toString();
    }
}
