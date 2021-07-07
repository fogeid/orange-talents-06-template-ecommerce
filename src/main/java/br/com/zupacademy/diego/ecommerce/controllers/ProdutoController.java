package br.com.zupacademy.diego.ecommerce.controllers;

import br.com.zupacademy.diego.ecommerce.dto.ProdutoFormDTO;
import br.com.zupacademy.diego.ecommerce.models.Produto;
import br.com.zupacademy.diego.ecommerce.repositories.CategoriaRepository;
import br.com.zupacademy.diego.ecommerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> inserir(@RequestBody @Valid ProdutoFormDTO dto) {
        Produto produto = dto.converter(categoriaRepository);
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }
}
