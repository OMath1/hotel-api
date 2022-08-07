package br.com.hotel;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ClientWeb {
    public void criaClient() {
        MultiValueMap<String, String> usuarioBody = new LinkedMultiValueMap<>();

        usuarioBody.add("Matheus", "49291038865");
    }
}