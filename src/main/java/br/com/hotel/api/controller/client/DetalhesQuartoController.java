package br.com.hotel.api.controller.client;

import br.com.hotel.api.dto.DetalhesQuartoResponse;
import br.com.hotel.domain.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quartos")
public class DetalhesQuartoController {

    @Autowired
    private QuartoRepository quartoRepository;

    @GetMapping
    public Page<DetalhesQuartoResponse> detalhar(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable paginacao) {
        var quarto = quartoRepository.findAll(paginacao);

        return DetalhesQuartoResponse.paraQuarto(quarto);
    }
}
