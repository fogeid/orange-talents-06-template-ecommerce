package br.com.zupacademy.diego.ecommerce.controllers;

import br.com.zupacademy.diego.ecommerce.dto.CategoriaFormDTO;
import br.com.zupacademy.diego.ecommerce.models.Categoria;
import br.com.zupacademy.diego.ecommerce.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Categoria> inserir(@RequestBody @Valid CategoriaFormDTO dto) {
        Categoria categoria = dto.converter(categoriaRepository);
        categoriaRepository.save(categoria);

        return ResponseEntity.ok().build();
    }
}
