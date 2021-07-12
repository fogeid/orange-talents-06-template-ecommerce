package br.com.zupacademy.diego.ecommerce.utils;

import br.com.zupacademy.diego.ecommerce.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EnviarEmailFake implements EnviarEmail {

    @Override
    public void enviarEmail(Usuario usuario) {
        System.out.println("Email enviado para " + usuario.getLogin());
    }
}
