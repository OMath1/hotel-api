package br.com.hotel.api.controller.client;

import br.com.hotel.api.dto.DetalhesReservaResponse;
import br.com.hotel.domain.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas/{idReserva}")
public class DetalhesReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    public ResponseEntity<DetalhesReservaResponse> detalhar(@PathVariable Long idReserva) {
        var reserva = reservaRepository.findById(idReserva);

        return reserva
                .map(value -> ResponseEntity.ok(new DetalhesReservaResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
