package br.com.hotel.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numeroDoQuarto;

    @Column(nullable = false)
    private Integer disponibilidade;

    @Deprecated
    public Quarto() {
    }

    public Quarto(Long id, Integer numeroDoQuarto, Integer disponibilidade) {
        this.id = id;
        this.numeroDoQuarto = numeroDoQuarto;
        this.disponibilidade = disponibilidade;
    }
}
