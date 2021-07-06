package br.com.zupacademy.diego.ecommerce.controllers;

import br.com.zupacademy.diego.ecommerce.dto.UsuarioFormDTO;
import br.com.zupacademy.diego.ecommerce.models.Usuario;
import br.com.zupacademy.diego.ecommerce.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> inserir(@RequestBody @Valid UsuarioFormDTO dto) {
        Usuario usuario = dto.converter();
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
