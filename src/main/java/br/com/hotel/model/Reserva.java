package br.com.hotel.model;

import lombok.Getter;
import lombok.Setter;

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

    @Setter
    @Column(nullable = false)
    private LocalDateTime checkin;
    @Setter
    @Column(nullable = false)
    private LocalDateTime checkout;

    private Integer qualidadeDeServico;

    @OneToOne(optional = false)
    private Usuario usuario;

//    @OneToOne(optional = false)
//    private Quarto quarto;

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
//        this.quarto = quarto;
    }

    public Reserva(Integer tempoEstadia, LocalDateTime dataReserva, Usuario usuario) {
        this.tempoEstadia = tempoEstadia;
        this.dataReserva = dataReserva;
        this.usuario = usuario;
    }
}
