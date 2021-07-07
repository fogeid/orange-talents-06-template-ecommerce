package br.com.zupacademy.diego.ecommerce.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "tb_produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @DecimalMin(value = "0.0")
    @Column(nullable = false)
    private BigDecimal valor;

    @NotNull
    @Min(value = 0)
    @Column(nullable = false)
    private Integer quantidadeDisponivel;

    @ElementCollection
    @MapKeyColumn(name = "descricao")
    @Size(min = 3)
    private Map<String, String> caracteristicas;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull @DecimalMin(value = "0.0") BigDecimal valor,
                   @NotNull @Min(value = 0) Integer quantidadeDisponivel,
                   @Size(min = 3) Map<String,String> caracteristicas, @NotBlank @Length(max = 1000) String descricao,
                   @NotNull Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
