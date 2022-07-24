package br.com.hotel.dto;

import br.com.hotel.model.Reserva;
import br.com.hotel.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class ReservaRequest {
    @NotNull
    @Positive
    private Integer tempoEstadia;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataReserva;

    public Reserva paraReserva (Usuario usuario) {
        return new Reserva(this.tempoEstadia, this.dataReserva, usuario);
    }
}
