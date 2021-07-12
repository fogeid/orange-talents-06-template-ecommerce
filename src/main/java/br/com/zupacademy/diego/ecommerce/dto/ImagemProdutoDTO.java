package br.com.zupacademy.diego.ecommerce.dto;

import br.com.zupacademy.diego.ecommerce.models.ImagemProduto;

public class ImagemProdutoDTO {
    private String link;

    public ImagemProdutoDTO(ImagemProduto imagemProduto) {
        this.link = imagemProduto.getLink();
    }

    public String getLink() {
        return link;
    }
}
