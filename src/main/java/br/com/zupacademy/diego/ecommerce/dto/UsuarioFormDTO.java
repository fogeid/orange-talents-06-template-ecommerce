package br.com.zupacademy.diego.ecommerce.dto;

import br.com.zupacademy.diego.ecommerce.models.Usuario;
import br.com.zupacademy.diego.ecommerce.validators.ValorUnico;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioFormDTO {
    @NotBlank
    @Email
    @ValorUnico(obj = Usuario.class, fieldName = "login", message = "Usuário já cadastrado, insira um outro usuário.")
    private String login;
    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioFormDTO() {
    }

    public UsuarioFormDTO(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario converter() {
        String senha = new BCryptPasswordEncoder().encode(this.senha);
        return new Usuario(this.login, senha);
    }
}
