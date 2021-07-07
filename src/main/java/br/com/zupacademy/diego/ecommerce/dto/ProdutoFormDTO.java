package br.com.zupacademy.diego.ecommerce.dto;

import br.com.zupacademy.diego.ecommerce.models.Categoria;
import br.com.zupacademy.diego.ecommerce.models.Produto;
import br.com.zupacademy.diego.ecommerce.repositories.CategoriaRepository;
import br.com.zupacademy.diego.ecommerce.validators.NaoCadastrado;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Map;

public class ProdutoFormDTO {
    @NotBlank
    private String nome;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal valor;

    @NotNull
    @Min(value = 0)
    private Integer quantidadeDisponivel;

    @Size(min = 3)
    private Map<String, String> caracteristicas;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @NaoCadastrado(obj = Categoria.class, fieldName = "id")
    private Long categoria;

    public ProdutoFormDTO() {
    }

    public ProdutoFormDTO(@NotBlank String nome, @NotNull @DecimalMin(value = "0.0") BigDecimal valor,
                          @NotNull Integer quantidadeDisponivel, @Size(min = 3) Map<String, String> caracteristicas,
                          @NotBlank @Length(max = 1000) String descricao, @NotNull Long categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public Map<String, String> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCategoria() {
        return categoria;
    }

    public Produto converter(CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.findById(this.categoria).get();
        return new Produto(this.nome, this.valor, this.quantidadeDisponivel,
                this.caracteristicas, this.descricao, categoria);
    }
}
