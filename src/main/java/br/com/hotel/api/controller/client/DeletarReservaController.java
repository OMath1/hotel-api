package br.com.hotel.api.controller.client;


import br.com.hotel.domain.repository.ReservaRepository;
import br.com.hotel.domain.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reservas/{idReserva}")
public class DeletarReservaController {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ReservaService reservaService;

    @DeleteMapping
    private void excluir (@PathVariable Long idReserva) {
        reservaService.excluirReserva(idReserva);
    }
}
