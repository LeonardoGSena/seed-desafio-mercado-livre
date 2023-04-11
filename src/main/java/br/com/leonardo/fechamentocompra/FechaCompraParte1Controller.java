package br.com.leonardo.fechamentocompra;

import br.com.leonardo.cadastroprodutos.Produto;
import br.com.leonardo.cadastrousuario.Usuario;
import br.com.leonardo.cadastrousuario.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Transactional
public class FechaCompraParte1Controller {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/compras")
    public String cria(@RequestBody @Valid NovaCompraRequest request, UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Produto produtoASerComprado = manager.find(Produto.class, request.getIdProduto());

        int quantidade = request.getQuantidade();
        boolean abateu = produtoASerComprado.abateEstoque(quantidade);
        if (abateu) {
            Usuario comprador = usuarioRepository.findByEmail("leonardo@deveficiente.com.br").get();
            GatewayPagamento gateway = request.getGateway();

            Compra novaCompra = new Compra(produtoASerComprado, quantidade, comprador, request.getGateway());
            manager.persist(novaCompra);
            if (gateway.equals(GatewayPagamento.PAGSEGURO)) {
                String urlRetornoPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(novaCompra.getId()).toString();
                return "paypal.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPagseguro;
            } else {
                String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}").buildAndExpand(novaCompra.getId()).toString();
                return "paypal.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPaypal;
            }
        }

        BindException problemaComEstoque = new BindException(request, "NovaCompraRequest");
        problemaComEstoque.reject(null, "Não foi possível realizar a comrpa por conta do estoque");

        throw  problemaComEstoque;
    }
}
