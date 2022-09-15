package br.com.hotel.api.controller.client;

import br.com.hotel.api.dto.AtualizarReservaRequest;
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

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/reservas/{idReserva}")
public class AtualizarReservaController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private QuartoRepository quartoRepository;
    @Autowired
    private ReservaService reservaService;

    @PutMapping
    public ResponseEntity<?> atualizar
            (
                    @PathVariable Long idReserva,
                    @RequestBody @Valid AtualizarReservaRequest atualizarReservaRequest,
                    UriComponentsBuilder uriComponentsBuilder
            ) {

        Reserva reserva = reservaRepository
                .findById(idReserva)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe o cadastro dessa reserva informada"));

        Reserva reservaModificada = atualizarReservaRequest.paraReserva(reserva);
        reservaService.atualizarPostagem(idReserva, reservaModificada);

        URI location = uriComponentsBuilder
                .path("/reservas/{idReserva}")
                .buildAndExpand(reserva.getId())
                .toUri();

        return ResponseEntity.ok(location);
    }

}
