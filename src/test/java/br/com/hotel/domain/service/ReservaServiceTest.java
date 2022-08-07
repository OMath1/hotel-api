package br.com.hotel.domain.service;

import br.com.hotel.domain.model.Reserva;
import br.com.hotel.domain.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservaServiceTest {
    private ReservaService reservaService;
    private Usuario usuario;

    @Test
    void deveriaLancarExcecaoSeADataDaReservaTemMaisDeTrintaDias() {
        var novaReserva = new Reserva(3, LocalDateTime.from(LocalDateTime.now().plusDays(31)), usuario);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> reservaService.fazReserva(novaReserva));

        assertEquals("Não é possível fazer uma reserva com mais de 30 dias de antecipação", illegalArgumentException.getMessage());
    }

    @Test
    void deveriaLancarExcecaoSeADataDaReservaFoiFeitaNoPassado() {
        var novaReserva = new Reserva(3, LocalDateTime.from(LocalDateTime.now().minusDays(1)), usuario);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> reservaService.fazReserva(novaReserva));

        assertEquals("Não é possível fazer uma reserva no passado", illegalArgumentException.getMessage());
    }

    @Test
    void deveriaLancarExcecaoSeOTempoDeEstadiaForMaiorQueTresDias() {
        var novaReserva = new Reserva(4, LocalDateTime.now().plusDays(1), usuario);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> reservaService.fazReserva(novaReserva));

        assertEquals("A estadia não pode ser maior que 3 dias ou menor ou igual a 0", illegalArgumentException.getMessage());
    }

    @Test
    void deveriaLancarExcecaoSeOTempoDeEstadiaForMenorOuIgualZero() {
        var expected = "A estadia não pode ser maior que 3 dias ou menor ou igual a 0";

        var reservaZero = new Reserva(0, LocalDateTime.now().plusDays(1), usuario);
        IllegalArgumentException exceptionZero = assertThrows(IllegalArgumentException.class, () -> reservaService.fazReserva(reservaZero));

        var reservaMenorQueZero = new Reserva(-1, LocalDateTime.now().plusDays(1), usuario);
        IllegalArgumentException exceptionMenorQueZero = assertThrows(IllegalArgumentException.class, () -> reservaService.fazReserva(reservaMenorQueZero));

        assertEquals(expected, exceptionZero.getMessage());
        assertEquals(expected, exceptionMenorQueZero.getMessage());
    }

    @BeforeEach
    void inicializar() {
        this.reservaService = new ReservaService();
        this.usuario = new Usuario("Alcino", "49291038865");
    }
}