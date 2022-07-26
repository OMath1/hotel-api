package br.com.hotel.api.controller;

import br.com.hotel.api.dto.UsuarioRequest;
import br.com.hotel.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class CadastrarUsuarioController {
    private final UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest, UriComponentsBuilder uri) {
        var novoUsuario = usuarioRequest.paraUsuario();

        usuarioRepository.save(novoUsuario);

        URI location = uri
                .path("/usuarios/{id}")
                .buildAndExpand(novoUsuario.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
