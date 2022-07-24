package br.com.hotel.model;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer tempoEstadia;

    @Column(nullable = false)
    private LocalDateTime dataReserva;

    @Column(nullable = false)
    private LocalDateTime checkin;

    @Column(nullable = false)
    private LocalDateTime checkout;

    @OneToOne
    private Usuario usuario;

    @OneToOne
    private Quarto quarto;

    @Deprecated
    public Reserva() {
    }

    public Reserva(Long id, Integer tempoEstadia, LocalDateTime dataReserva, LocalDateTime checkin, LocalDateTime checkout, Usuario usuario, Quarto quarto) {
        this.id = id;
        this.tempoEstadia = tempoEstadia;
        this.dataReserva = dataReserva;
        this.checkin = checkin;
        this.checkout = checkout;
        this.usuario = usuario;
        this.quarto = quarto;
    }
}
