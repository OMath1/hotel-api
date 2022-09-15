package br.com.hotel.api.controller.client;

import br.com.hotel.api.dto.DetalhesReservaResponse;
import br.com.hotel.api.dto.ReservaRequest;
import br.com.hotel.domain.model.Quarto;
import br.com.hotel.domain.model.Reserva;
import br.com.hotel.domain.model.Usuario;
import br.com.hotel.domain.repository.QuartoRepository;
import br.com.hotel.domain.repository.ReservaRepository;
import br.com.hotel.domain.repository.UsuarioRepository;
import br.com.hotel.domain.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/usuarios/{idUsuario}/quartos/{idQuarto}/reservas")
public class CadastrarReservaController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<?> cadastrar(
            @PathVariable Long idQuarto,
            @PathVariable Long idUsuario,
            @RequestBody @Valid ReservaRequest reservaDto,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Usuario usuario = usuarioRepository
                .findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe cadastro de usuário para o id informado"));

        Quarto quarto = quartoRepository
                .findById(idQuarto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe cadastro de quarto para o id informado"));

        Reserva novaReserva = reservaDto.paraReserva(usuario, quarto);

        reservaService.fazReserva(novaReserva);

        URI location = uriComponentsBuilder
                .path("/usuarios/{id}/reservas/{idReserva}")
                .buildAndExpand(usuario.getId(), novaReserva.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
