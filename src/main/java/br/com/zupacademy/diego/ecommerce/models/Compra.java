package br.com.zupacademy.diego.ecommerce.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PagamentoGateway pagamentoGateway;

    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra = StatusCompra.INICIADO;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    public Compra() {
    }

    public Compra(Integer quantidade, PagamentoGateway pagamentoGateway, StatusCompra statusCompra, Produto produto, Usuario usuario) {
        this.quantidade = quantidade;
        this.pagamentoGateway = pagamentoGateway;
        this.statusCompra = statusCompra;
        this.produto = produto;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public PagamentoGateway getPagamentoGateway() {
        return pagamentoGateway;
    }

    public StatusCompra getStatusCompra() {
        return statusCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
