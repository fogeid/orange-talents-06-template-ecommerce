package br.com.zupacademy.diego.ecommerce.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginFormDTO {
    private String login;
    private String senha;

    public LoginFormDTO() {
    }

    public LoginFormDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(this.login, this.senha);
    }
}
