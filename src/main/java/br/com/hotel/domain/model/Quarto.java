package br.com.hotel.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean disponibilidade;
    @Column(nullable = false)
    private Integer numeroDoQuarto;


    @Deprecated
    public Quarto() {
    }

    public Quarto(Integer numeroDoQuarto, Boolean disponibilidade) {
        this.numeroDoQuarto = numeroDoQuarto;
        this.disponibilidade = disponibilidade;
    }
}
