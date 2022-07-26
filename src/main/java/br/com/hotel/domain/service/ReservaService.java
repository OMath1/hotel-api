package br.com.hotel.domain.service;

import br.com.hotel.api.exception.EstadiaInvalidaException;
import br.com.hotel.api.exception.ReservaAntecipadaException;
import br.com.hotel.api.exception.ReservaNoPassadoException;
import br.com.hotel.domain.model.Reserva;
import br.com.hotel.domain.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;

    public void fazReserva(Reserva reserva) {
        var dataAtual = LocalDateTime.now();
        var dataDaReserva = reserva.getDataReserva();

        var numDeDiasEntre = DAYS.between(dataAtual, dataDaReserva);

        if (numDeDiasEntre > 30 ) {
            throw new ReservaAntecipadaException("Não e possível fazer uma reserva com mais de 30 dias de antecipação");
        } else if (numDeDiasEntre < 0) {
            throw new ReservaNoPassadoException("Não e possível fazer uma reserva no passado");
        }
        if (reserva.getTempoEstadia() > 3 || reserva.getTempoEstadia() <= 0) {
            throw new EstadiaInvalidaException("A estadia não pode ser maior que 3 dias ou menor ou igual a 0, a estadia inserido foi " + reserva.getTempoEstadia());
        }

        reserva.setCheckin(dataDaReserva.plusDays(1L));
        reserva.setCheckout(reserva.getCheckin().plusDays(reserva.getTempoEstadia()));

        reservaRepository.save(reserva);
    }
}

