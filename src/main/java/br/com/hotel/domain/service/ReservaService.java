package br.com.hotel.domain.service;

import br.com.hotel.domain.model.Reserva;
import br.com.hotel.domain.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public void fazReserva(Reserva reserva) {
        var dataAtual = LocalDateTime.now();
        var dataDaReserva = reserva.getDataReserva();

        if (dataDaReserva.isAfter(dataAtual.plusDays(30))) {
            throw new IllegalArgumentException("Não é possível fazer uma reserva com mais de 30 dias de antecipação");
        } else if (dataDaReserva.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível fazer uma reserva no passado");
        }
        if (reserva.getTempoEstadia() > 3 || reserva.getTempoEstadia() <= 0) {
            throw new IllegalArgumentException("A estadia não pode ser maior que 3 dias ou menor ou igual a 0");
        }

        reserva.setCheckin(dataDaReserva.plusDays(1L));
        reserva.setCheckout(reserva.getCheckin().plusDays(reserva.getTempoEstadia()));

        reservaRepository.save(reserva);
    }


}

