package br.com.zupacademy.diego.ecommerce.dto;

import br.com.zupacademy.diego.ecommerce.models.Categoria;
import br.com.zupacademy.diego.ecommerce.repositories.CategoriaRepository;
import br.com.zupacademy.diego.ecommerce.validators.ValorUnico;

import javax.validation.constraints.NotBlank;

public class CategoriaFormDTO {
    @NotBlank
    @ValorUnico(obj = Categoria.class, fieldName = "nome", message = "Categoria já cadastrada, insira uma outra categoria.")
    private String nome;

    private Long categoriaMae;

    public CategoriaFormDTO() {
    }

    public CategoriaFormDTO(@NotBlank String nome, Long categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMae() {
        return categoriaMae;
    }

    public Categoria converter(CategoriaRepository categoriaRepository) {
        Categoria categoria = null;
        if (this.categoriaMae != null) {
            categoria = categoriaRepository.findById(this.categoriaMae).get();
        }

        return new Categoria(this.nome, categoria);
    }
}
