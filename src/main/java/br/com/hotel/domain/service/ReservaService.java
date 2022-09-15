package br.com.hotel.domain.service;

import br.com.hotel.domain.model.Reserva;
import br.com.hotel.domain.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public void fazReserva(Reserva reserva) {
        verificaDatas(reserva);
        disponibilidadeQuarto(reserva);

        reserva.getQuarto().setDisponibilidade(false);
        reservaRepository.save(reserva);
    }

    public void atualizarReserva(Long idReserva, Reserva reservaRecebida) {

        Reserva reservaAtualizada = verificarReserva(idReserva);

        reservaAtualizada.setDataReserva(reservaRecebida.getDataReserva());
        reservaAtualizada.setTempoEstadia(reservaRecebida.getTempoEstadia());

        verificaDatas(reservaAtualizada);

        reservaRepository.save(reservaAtualizada);
    }

    public void excluirReserva(Long idReserva) {
        Reserva reserva = verificarReserva(idReserva);

        reserva.getQuarto().setDisponibilidade(true);

        reservaRepository.delete(reserva);
    }

    private void verificaDatas(Reserva reserva) {
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
    }

    private void disponibilidadeQuarto(Reserva reserva) {
        if (!reserva.getQuarto().getDisponibilidade()) {
            throw new IllegalArgumentException("O quarto escolhido não esta disponivel");
        }
    }

    private Reserva verificarReserva(Long idReserva) {
        Optional<Reserva> reservaCadastrada = reservaRepository.findById(idReserva);

        if (reservaCadastrada.isPresent()) {
            return reservaCadastrada.get();
        } else {
            throw new IllegalArgumentException("Postagem não encontrada");
        }
    }
}

