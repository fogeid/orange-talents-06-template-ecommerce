package br.com.zupacademy.diego.ecommerce.dto;

import br.com.zupacademy.diego.ecommerce.models.Pergunta;
import br.com.zupacademy.diego.ecommerce.models.Produto;
import br.com.zupacademy.diego.ecommerce.models.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaFormDTO {
    @NotBlank
    private String titulo;

    public PerguntaFormDTO() {
    }

    public PerguntaFormDTO(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Pergunta converter(Produto produto, Usuario usuario) {
        return new Pergunta(this.titulo, usuario, produto);
    }
}
