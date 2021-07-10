package br.com.zupacademy.diego.ecommerce.models;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_imagem_produto")
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotBlank
    @URL
    private String link;

    public ImagemProduto() {
    }

    public ImagemProduto(@NotNull Produto produto, @NotBlank @URL String link) {
        this.produto = produto;
        this.link = link;
    }
}
