package br.com.hotel.api.dto;

import br.com.hotel.domain.model.Quarto;
import br.com.hotel.domain.model.Reserva;
import br.com.hotel.domain.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class ReservaRequest {
    @NotNull
    @Positive
    private Integer tempoEstadia;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataReserva;

    public Reserva paraReserva (Usuario usuario, Quarto quarto) {
        return new Reserva(this.tempoEstadia, this.dataReserva, usuario, quarto);
    }
}
