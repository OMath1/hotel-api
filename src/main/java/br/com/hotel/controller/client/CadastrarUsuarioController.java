package br.com.hotel.controller.client;

import br.com.hotel.dto.UsuarioRequest;
import br.com.hotel.repository.UsuarioRepository;
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
