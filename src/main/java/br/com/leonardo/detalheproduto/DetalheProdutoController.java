package br.com.leonardo.detalheproduto;

import br.com.leonardo.cadastroprodutos.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetalheProdutoController {

    @PersistenceContext
    private EntityManager manager;
    
    @GetMapping(value = "/produtos/{id}")
    private DetalheProdutoView produtoById(@PathVariable("id") Long id) {
        Produto produtoEscolhido = manager.find(Produto.class, id);
        return new DetalheProdutoView(produtoEscolhido);
    }
}
