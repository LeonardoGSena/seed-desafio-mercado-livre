package br.com.leonardo.cadastrousuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;
    @PastOrPresent
    private LocalDateTime instanteCriacao;

    private Usuario() {
    }

    public Usuario(@Email @NotBlank String email, @NotBlank @Length(min = 6) SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(email), "email não pode ser em branco");
        Assert.notNull(senhaLimpa, "o objeto do tipo senha limpa não pode ser nulo");

        this.email = email;
        this.senha = senhaLimpa.hash();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
