package br.com.hotel.controller.client;

import br.com.hotel.dto.DetalhesReservaResponse;
import br.com.hotel.dto.ReservaRequest;
import br.com.hotel.model.Reserva;
import br.com.hotel.model.Usuario;
import br.com.hotel.repository.ReservaRepository;
import br.com.hotel.repository.UsuarioRepository;
import br.com.hotel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/usuarios/{id}/reservas")
public class CadastrarReservaController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<DetalhesReservaResponse> cadastrar(
            @PathVariable Long id,
            @RequestBody @Valid ReservaRequest reservaDto,
            UriComponentsBuilder uriComponentsBuilder
    ) {

        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe cadastro de usuário para o id informado"));

        Reserva novaReserva = reservaDto.paraReserva(usuario);

        reservaService.fazReserva(novaReserva);

        URI location = uriComponentsBuilder
                .path("/usuarios/{id}/reservas/{idReserva}")
                .buildAndExpand(usuario.getId(), novaReserva.getId())
                .toUri();

        return ResponseEntity.created(location).body(new DetalhesReservaResponse(novaReserva));
    }
}
