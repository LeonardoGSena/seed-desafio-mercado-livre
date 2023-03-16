package br.com.leonardo.cadastrousuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class NovoUsuarioRequest {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Length(min = 6)
    private String senha;

    public NovoUsuarioRequest(@Email @NotBlank String email, @NotBlank @Length(min = 6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario toUsuario() {
        return new Usuario(email, new SenhaLimpa(senha));
    }

    public String getEmail() {
        return email;
    }
}
