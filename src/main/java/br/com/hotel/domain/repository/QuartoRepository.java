package br.com.hotel.domain.repository;

import br.com.hotel.domain.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {
}
