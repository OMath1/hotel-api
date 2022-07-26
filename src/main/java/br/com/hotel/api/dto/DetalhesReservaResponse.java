package br.com.hotel.api.dto;

import br.com.hotel.domain.model.Reserva;
import br.com.hotel.domain.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DetalhesReservaResponse {
    private Integer tempoEstadia;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataReserva;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime checkin;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime checkout;

    private Integer qualidadeDeServico;

    private Usuario usuario;

//    private Quarto quarto;

    public DetalhesReservaResponse(Reserva reserva) {
        this.tempoEstadia = reserva.getTempoEstadia();
        this.dataReserva = reserva.getDataReserva();
        this.checkin = reserva.getCheckin();
        this.checkout = reserva.getCheckout();
        this.qualidadeDeServico = reserva.getQualidadeDeServico();
        this.usuario = reserva.getUsuario();
//        this.quarto = reserva.getQuarto();
    }
}
