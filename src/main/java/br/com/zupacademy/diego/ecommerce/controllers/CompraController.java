package br.com.zupacademy.diego.ecommerce.controllers;

import br.com.zupacademy.diego.ecommerce.dto.CompraFormDTO;
import br.com.zupacademy.diego.ecommerce.models.Compra;
import br.com.zupacademy.diego.ecommerce.models.Produto;
import br.com.zupacademy.diego.ecommerce.models.Usuario;
import br.com.zupacademy.diego.ecommerce.repositories.CompraRepository;
import br.com.zupacademy.diego.ecommerce.repositories.ProdutoRepository;
import br.com.zupacademy.diego.ecommerce.utils.EnviarEmailFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EnviarEmailFake enviarEmailFake;

    @PostMapping
    @Transactional
    public ResponseEntity<?> compra(@RequestBody @Valid CompraFormDTO dto, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = produtoRepository.findById(dto.getProduto()).get();

        if (produto.abaterEstoque(dto.getQuantidade())) {
            produtoRepository.save(produto);

            Compra compra = dto.converter(produtoRepository, usuario);
            compraRepository.save(compra);

            enviarEmailFake.enviarEmail(usuario);
            return ResponseEntity.status(HttpStatus.FOUND).body(compra.getPagamentoGateway().getUrl(compra.getId()));
        }

        return ResponseEntity.badRequest().body("O produto " + produto.getNome() + " não tem quantidade suficiente");
    }
}
