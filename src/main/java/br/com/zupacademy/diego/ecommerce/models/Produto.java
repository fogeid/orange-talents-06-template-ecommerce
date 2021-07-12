package br.com.zupacademy.diego.ecommerce.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ManyToOne
    Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto")
    private Set<Opniao> opnioes = new HashSet<>();

    @OneToMany(mappedBy = "produto")
    private Set<Pergunta> perguntas = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull @DecimalMin(value = "0.0") BigDecimal valor,
                   @NotNull @Min(value = 0) Integer quantidadeDisponivel,
                   @Size(min = 3) Map<String,String> caracteristicas, @NotBlank @Length(max = 1000) String descricao,
                   @NotNull Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public Set<Opniao> getOpnioes() {
        return opnioes;
    }

    public Set<Pergunta> getPerguntas() {
        return perguntas;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Integer getTotalOpnioes() {
        return this.opnioes.size();
    }

    public Double getMediaNotas() {
        return this.opnioes.stream().map(Opniao::getNota).collect(Collectors.averagingInt(i -> i));
    }

    public void adicionarImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public boolean pertence(Usuario usuario) {
        return usuario.getId() == this.usuario.getId();
    }
}
