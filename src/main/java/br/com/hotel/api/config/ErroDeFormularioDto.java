package br.com.hotel.api.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

@AllArgsConstructor
@Getter
public class ErroDeFormularioDto {
    private String campo;
    private String erro;
}