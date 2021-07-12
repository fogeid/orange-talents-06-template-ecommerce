package br.com.zupacademy.diego.ecommerce.dto;

import br.com.zupacademy.diego.ecommerce.models.Opniao;

public class OpniaoDTO {
    private Integer nota;
    private String titulo;
    private String descricao;

    public OpniaoDTO(Opniao opniao) {
        this.nota = opniao.getNota();
        this.titulo = opniao.getTitulo();
        this.descricao = opniao.getDescricao();
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
