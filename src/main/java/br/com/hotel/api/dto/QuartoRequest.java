package br.com.hotel.api.dto;

import br.com.hotel.domain.model.Quarto;
import br.com.hotel.domain.model.Reserva;
import br.com.hotel.domain.model.Usuario;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data

public class QuartoRequest {
    @NotNull
    private Boolean disponibilidade;
    @NotNull
    @Positive
    private Integer numeroDoQuarto;

    public Quarto paraQuarto () {
        return new Quarto(this.numeroDoQuarto, this.disponibilidade);
    }
}
