package br.com.hotel.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer tempoEstadia;

    @Column(nullable = false)
    private LocalDateTime dataReserva;

    @Setter
    @Column(nullable = false)
    private LocalDateTime checkin;
    @Setter
    @Column(nullable = false)
    private LocalDateTime checkout;

    private Integer qualidadeDeServico;

    @OneToOne(optional = false)
    private Usuario usuario;

    @OneToOne(optional = false)
    private Quarto quarto;

    @Deprecated
    public Reserva() {
    }

    public Reserva(
            Long id, Integer tempoEstadia, LocalDateTime dataReserva,
            LocalDateTime checkin, LocalDateTime checkout, Integer qualidadeDeServico,
            Usuario usuario, Quarto quarto
    ) {
        this.id = id;
        this.tempoEstadia = tempoEstadia;
        this.dataReserva = dataReserva;
        this.checkin = checkin;
        this.checkout = checkout;
        this.qualidadeDeServico = qualidadeDeServico;
        this.usuario = usuario;
        this.quarto = quarto;
    }

    public Reserva(Integer tempoEstadia, LocalDateTime dataReserva, Usuario usuario, Quarto quarto) {
        this.tempoEstadia = tempoEstadia;
        this.dataReserva = dataReserva;
        this.usuario = usuario;
        this.quarto = quarto;
    }

    public Reserva(Integer tempoEstadia, LocalDateTime data, Usuario usuario) {
        this.tempoEstadia = tempoEstadia;
        this.dataReserva = data;
        this.usuario = usuario;
    }

    public Reserva(Integer tempoEstadia, LocalDateTime dataReserva, Reserva reserva) {
        this.tempoEstadia = tempoEstadia;
        this.dataReserva = dataReserva;
    }
}
