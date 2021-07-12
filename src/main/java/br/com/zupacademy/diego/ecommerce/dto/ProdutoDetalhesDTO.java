package br.com.zupacademy.diego.ecommerce.dto;

import br.com.zupacademy.diego.ecommerce.models.Produto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoDetalhesDTO {
    private Set<ImagemProdutoDTO> links;
    private String nome;
    private BigDecimal valor;
    private Integer quantidadeDisponivel;
    private Map<String, String> caracteristicas = new HashMap<>();
    private String descricao;
    private Integer totoalOpnioes;
    private Double mediaNotasOpniao;
    private Set<OpniaoDTO> opnioes = new HashSet<>();
    private Set<PerguntaDTO> perguntas = new HashSet<>();

    public ProdutoDetalhesDTO(Produto produto) {
        this.links = produto.getImagens().stream().map(ImagemProdutoDTO::new).collect(Collectors.toSet());
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidadeDisponivel = produto.getQuantidadeDisponivel();
        this.caracteristicas = produto.getCaracteristicas();
        this.descricao = produto.getDescricao();
        this.totoalOpnioes = produto.getTotalOpnioes();
        this.mediaNotasOpniao = produto.getMediaNotas();
        this.opnioes = produto.getOpnioes().stream().map(OpniaoDTO::new).collect(Collectors.toSet());
        this.perguntas = produto.getPerguntas().stream().map(PerguntaDTO::new).collect(Collectors.toSet());
    }

    public Set<ImagemProdutoDTO> getLinks() {
        return links;
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

    public Integer getTotoalOpnioes() {
        return totoalOpnioes;
    }

    public Double getMediaNotasOpniao() {
        return mediaNotasOpniao;
    }

    public Set<OpniaoDTO> getOpnioes() {
        return opnioes;
    }

    public Set<PerguntaDTO> getPerguntas() {
        return perguntas;
    }
}
