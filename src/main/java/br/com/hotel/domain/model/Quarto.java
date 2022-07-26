package br.com.hotel.domain.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
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

    public Quarto(Long id, Integer numeroDoQuarto, Boolean disponibilidade) {
        this.id = id;
        this.numeroDoQuarto = numeroDoQuarto;
        this.disponibilidade = disponibilidade;
    }
}
