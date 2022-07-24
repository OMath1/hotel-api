package br.com.hotel.service;

import br.com.hotel.exception.ReservaAntencipadaException;
import br.com.hotel.model.Reserva;
import br.com.hotel.repository.QuartoRepository;
import br.com.hotel.repository.ReservaRepository;
import br.com.hotel.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final QuartoRepository quartoRepository;
    private final UsuarioRepository usuarioRepository;

    public Reserva estadia(Reserva reserva) {
        var trintaDias = LocalDateTime.now().minusDays(30L);

        if(reserva.getDataReserva().isBefore(trintaDias)) {
            throw new ReservaAntencipadaException("Nao e possivel fazer uma reserva com 30 de antecipaçao");
        }

        return reservaRepository.save(reserva);

//        if (reserva.getTempoEstadia() > 3) {
//            throw new ReservaAntecipadaException();
//        } else if (reserva.getTempoEstadia() <= 3) {
//           dado a data de reserva, somar o dia em que a reserva começa (check-in) terminando no dia em que ela finaliza (check-out)
            // 1. data reservada começa no dia posterior a sua reserva, assim tambem o check-in
            // 2. checkout e = a data do check-in + tempoEstadia
//        }
//        verifica se a data reservada e igual ou menor que 30 dias*/

    }

    public static void main(String[] args) {
        var trintaDias = LocalDateTime.now().minusDays(30L);
        var reserva = LocalDateTime.of(2022, Month.JUNE, 24, 23, 59);

        if(reserva.isBefore(trintaDias)) {
            System.out.println("Nao foi possivel fazer sua reserva por ela ter mais de 30 dias");
        }
    }
}
