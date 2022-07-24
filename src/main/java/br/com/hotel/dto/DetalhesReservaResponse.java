package br.com.hotel.dto;

import br.com.hotel.model.Quarto;
import br.com.hotel.model.Reserva;
import br.com.hotel.model.Usuario;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class DetalhesReservaResponse {
    private Integer tempoEstadia;

    private LocalDateTime dataReserva;

    private LocalDateTime checkin;

    private LocalDateTime checkout;

    private Integer qualidadeDeServico;

    private Usuario usuario;

    private Quarto quarto;

    public DetalhesReservaResponse(Reserva reserva) {
        this.tempoEstadia = reserva.getTempoEstadia();
        this.dataReserva = reserva.getDataReserva();
        this.checkin = reserva.getCheckin();
        this.checkout = reserva.getCheckout();
        this.qualidadeDeServico = reserva.getQualidadeDeServico();
        this.usuario = reserva.getUsuario();
        this.quarto = reserva.getQuarto();
    }
}
