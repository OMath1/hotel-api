package br.com.hotel.api.controller.itdpto;

import br.com.hotel.api.dto.DetalhesQuartoResponse;
import br.com.hotel.api.dto.QuartoRequest;
import br.com.hotel.domain.repository.QuartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/quartos")
public class CadastrarQuartoController {

    @Autowired
    private QuartoRepository quartoRepository;

    @PostMapping
    public ResponseEntity<DetalhesQuartoResponse> cadastrar (@RequestBody @Valid QuartoRequest quartoRequest, UriComponentsBuilder uri) {
        var novoQuarto = quartoRequest.paraQuarto();

        quartoRepository.save(novoQuarto);

        URI location = uri
                .path("/quartos/{id}")
                .buildAndExpand(novoQuarto.getId())
                .toUri();

        return ResponseEntity.created(location).body(new DetalhesQuartoResponse(novoQuarto));
    }
}
