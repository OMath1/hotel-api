package br.com.hotel.api.dto;

import br.com.hotel.domain.model.Quarto;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class DetalhesQuartoResponse {
    private Boolean disponibilidade;
    private Integer numeroDoQuarto;

    public DetalhesQuartoResponse(Quarto quarto) {
        this.disponibilidade = quarto.getDisponibilidade();
        this.numeroDoQuarto = quarto.getNumeroDoQuarto();
    }
}
