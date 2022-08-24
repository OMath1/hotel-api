package br.com.hotel.api.dto;

import br.com.hotel.domain.model.Quarto;
import lombok.Getter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class DetalhesQuartoResponse {
    private Long id;
    private Boolean disponibilidade;
    private Integer numeroDoQuarto;

    public DetalhesQuartoResponse(Quarto quarto) {
        this.id = quarto.getId();
        this.disponibilidade = quarto.getDisponibilidade();
        this.numeroDoQuarto = quarto.getNumeroDoQuarto();
    }

    public static Page<DetalhesQuartoResponse> paraQuarto(Page<Quarto> quarto) {
        return quarto.map(DetalhesQuartoResponse::new);
    }
}
