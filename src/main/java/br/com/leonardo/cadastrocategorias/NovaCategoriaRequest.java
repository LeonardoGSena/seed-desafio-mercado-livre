package br.com.leonardo.cadastrocategorias;

import br.com.leonardo.compartilhado.ExistsId;
import br.com.leonardo.compartilhado.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.util.Assert;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass=Categoria.class, fieldName="nome")
    private String nome;
    @Positive
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    @Override
    public String toString() {
        return "NovaCategoriaRequest{" +
                "nome='" + nome + '\'' +
                ", idCategoriaMae=" + idCategoriaMae +
                '}';
    }

    public Categoria toModel(EntityManager manager) {
        Categoria categoria = new Categoria(nome);
        if (idCategoriaMae != null) {
            Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
            Assert.notNull(categoriaMae, "o id da categoria mãe preciso ser válido");
            categoria.setMae(categoriaMae);
        }
        return categoria;
    }
}
