package br.com.zupacademy.diego.ecommerce.dto;

import br.com.zupacademy.diego.ecommerce.models.*;
import br.com.zupacademy.diego.ecommerce.repositories.ProdutoRepository;
import br.com.zupacademy.diego.ecommerce.validators.NaoCadastrado;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraFormDTO {
    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    private PagamentoGateway pagamentoGateway;

    private StatusCompra statusCompra = StatusCompra.INICIADO;

    @NotNull
    @NaoCadastrado(obj = Produto.class, fieldName = "id")
    private Long produto;

    public CompraFormDTO() {
    }

    public CompraFormDTO(@NotNull @Positive Integer quantidade, @NotNull PagamentoGateway pagamentoGateway, @NotNull Long produto) {
        this.quantidade = quantidade;
        this.pagamentoGateway = pagamentoGateway;
        this.statusCompra = StatusCompra.INICIADO;
        this.produto = produto;
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

    public Long getProduto() {
        return produto;
    }

    public Compra converter(ProdutoRepository produtoRepository, Usuario usuario) {
        Produto pdt = produtoRepository.getById(produto);
        return new Compra(this.quantidade, this.pagamentoGateway, this.statusCompra, pdt, usuario);
    }
}
