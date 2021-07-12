package br.com.zupacademy.diego.ecommerce.controllers;

import br.com.zupacademy.diego.ecommerce.dto.*;
import br.com.zupacademy.diego.ecommerce.models.Opniao;
import br.com.zupacademy.diego.ecommerce.models.Pergunta;
import br.com.zupacademy.diego.ecommerce.models.Produto;
import br.com.zupacademy.diego.ecommerce.models.Usuario;
import br.com.zupacademy.diego.ecommerce.repositories.CategoriaRepository;
import br.com.zupacademy.diego.ecommerce.repositories.OpniaoRepository;
import br.com.zupacademy.diego.ecommerce.repositories.PerguntaRepository;
import br.com.zupacademy.diego.ecommerce.repositories.ProdutoRepository;
import br.com.zupacademy.diego.ecommerce.utils.EnviarEmailFake;
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
    private OpniaoRepository opniaoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private UploaderFake uploaderFake;

    @Autowired
    private EnviarEmailFake enviarEmailFake;

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> inserir(@RequestBody @Valid ProdutoFormDTO dto, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = dto.converter(categoriaRepository, usuario);
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/imagens")
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

    @PostMapping("/{id}/opnioes")
    @Transactional
    public ResponseEntity<?> inserirOpniao(@PathVariable("id") Long id, @RequestBody @Valid OpniaoFormDTO dto, @AuthenticationPrincipal Usuario usuario) {
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Opniao opniao = dto.converter(produto.get(), usuario);

        opniaoRepository.save(opniao);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/perguntas")
    @Transactional
    public ResponseEntity<?> inserirPergunta(@PathVariable("id") Long id, @RequestBody @Valid PerguntaFormDTO dto, @AuthenticationPrincipal Usuario usuario) {
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pergunta pergunta = dto.converter(produto.get(), usuario);
        perguntaRepository.save(pergunta);
        enviarEmailFake.enviarEmail(usuario);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDetalhesDTO> detalhes(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {
            return ResponseEntity.ok(new ProdutoDetalhesDTO(produto.get()));
        }

        return ResponseEntity.notFound().build();
    }
}
