package br.com.hotel.dto;

import br.com.hotel.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
public class UsuarioRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @CPF
    private String cpf;

    public Usuario paraUsuario() {
        return new Usuario(this.nome, this.cpf);
    }
}
