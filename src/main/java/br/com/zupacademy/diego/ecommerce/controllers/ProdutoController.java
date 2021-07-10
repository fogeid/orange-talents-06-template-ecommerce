package br.com.zupacademy.diego.ecommerce.controllers;

import br.com.zupacademy.diego.ecommerce.dto.ImagemFormDTO;
import br.com.zupacademy.diego.ecommerce.dto.ProdutoFormDTO;
import br.com.zupacademy.diego.ecommerce.models.Produto;
import br.com.zupacademy.diego.ecommerce.models.Usuario;
import br.com.zupacademy.diego.ecommerce.repositories.CategoriaRepository;
import br.com.zupacademy.diego.ecommerce.repositories.ProdutoRepository;
import br.com.zupacademy.diego.ecommerce.utils.UploaderFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UploaderFake uploaderFake;

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> inserir(@RequestBody @Valid ProdutoFormDTO dto, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = dto.converter(categoriaRepository, usuario);
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public ResponseEntity<Set<String>> inserirImagens(@PathVariable("id") Long id, @Valid ImagemFormDTO dto, @AuthenticationPrincipal Usuario usuario) {
        Set<String> links = uploaderFake.enviar(dto.getImages());
        Optional<Produto> optProduto = produtoRepository.findById(id);
        if (optProduto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Produto produto = optProduto.get();

        if (!produto.pertence(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        produto.adicionarImagens(links);
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }
}
