package br.com.hotel.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestControllerAdvice
public class ErroDeFormularioHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
        List<ErroDeFormularioDto> dto = new ArrayList<>();

        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
        fieldError
                .forEach(
                        e -> {
                            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
                            ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
                            dto.add(erro);
                        });
        return dto;
    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(IllegalArgumentException.class)
    public List<String> illegalArgumentException(IllegalArgumentException exception) {
        String bindingResult = exception.getMessage();

        List<String> mensagens = new ArrayList<>();
        mensagens.add(bindingResult);

        return mensagens;
    }
}
